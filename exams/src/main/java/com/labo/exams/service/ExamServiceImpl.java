package com.labo.exams.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.exams.clients.CatalogClient;
import com.labo.exams.clients.PatientClient;
import com.labo.exams.dto.AreaTo;
import com.labo.exams.dto.DatosTo;
import com.labo.exams.dto.ExamReportTo;
import com.labo.exams.dto.ExamTo;
import com.labo.exams.dto.PatientTo;
import com.labo.exams.dto.PruebasReportTo;
import com.labo.exams.dto.PruebasTo;
import com.labo.exams.repo.IExamRepo;
import com.labo.exams.repo.model.Exam;
import com.labo.exams.repo.model.Test;

import reactor.core.publisher.Mono;

@Service
public class ExamServiceImpl implements IExamService {

    @Autowired
    private PatientClient patientServiceClient;
    @Autowired
    private IExamRepo examRepo;
    @Autowired
    private CatalogClient catalogServiceClient;

    @Override
    public List<DatosTo> showPendingExams(Long areaId) {
        List<Exam> exams = this.examRepo.buscarEstados(false);
        return exams.stream()
                .filter(exam -> exam.getTests() != null && !exam.getTests().isEmpty())
                .map(exam -> mapToDataTo(exam, areaId))
                .filter(datosTo -> datosTo != null && datosTo.getPruebas() != null && !datosTo.getPruebas().isEmpty())
                .collect(Collectors.toList());
    }

    private DatosTo mapToDataTo(Exam exam, Long areaId) {
        DatosTo datosTo = new DatosTo();
        datosTo.setIdExamen(exam.getId());
        Mono<PatientTo> patientMono = patientServiceClient.getPatientById(exam.getPatientId());
        PatientTo patient = patientMono.block();
        datosTo.setApellido(patient.getApellido());
        datosTo.setEstado(exam.getStatus());

        // Map and filter tests in one step, removing any nulls
        List<PruebasTo> pruebas = exam.getTests().stream()
                .map(test -> mapToPruebasTo(test, areaId))
                .filter(Objects::nonNull) // This removes any null entries
                .collect(Collectors.toList());

        datosTo.setPruebas(pruebas);

        // If after filtering there are no tests left, return null so this exam can be
        // filtered out
        if (pruebas.isEmpty()) {
            return null;
        }

        return datosTo;
    }

    private PruebasTo mapToPruebasTo(Test test, Long areaId) {
        PruebasTo pruebasTo = new PruebasTo();
        var catalog = this.catalogServiceClient.getCatalogById(test.getTestId()).block();

        if (catalog.getIdArea() != areaId) {
            // Test doesn't belong to the specified area, so we exclude it
            return null;
        } else {
            pruebasTo.setId(test.getTestId());
            pruebasTo.setNombrePrueba(catalog.getTestName());
            pruebasTo.setValor(null);
            return pruebasTo;
        }
    }

    @Override
    public List<ExamTo> buscarTodos() {
        return this.examRepo.buscarTodos().stream()
                .map(e -> {
                    ExamTo examTo = new ExamTo();
                    examTo.setId(e.getId());
                    examTo.setPatientId(e.getPatientId());
                    examTo.setUserId(e.getUserId());
                    return examTo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void completarExamen(DatosTo datos) {
        // Step 1: Map testId -> PruebasTo for fast access
        Map<Long, PruebasTo> pruebaMap = datos.getPruebas().stream()
                .collect(Collectors.toMap(PruebasTo::getId, p -> p));

        // Step 2: Fetch the exam
        Exam e = this.examRepo.searchExamById(datos.getIdExamen());

        // Step 3: Update tests with matching data
        for (Test test : e.getTests()) {
            PruebasTo pruebaDato = pruebaMap.get(test.getId());
            if (pruebaDato != null) {
                test.setResult(pruebaDato.getValor());
                test.setCompletionDate(LocalDateTime.now());
            }
        }

        // Step 4: Update status and save
        this.examRepo.updateExam(e);
    }

    @Override
    public void crearExamen(Exam e) {
        this.examRepo.createExam(e);
    }

    @Override
    public ExamReportTo buscarReportId(Long id) {

        var e = this.examRepo.searchExamById(id);
        var patient = this.patientServiceClient.getPatientById(e.getPatientId()).block();
        ExamReportTo reporte = new ExamReportTo();

        List<PruebasReportTo> pruebasList = new ArrayList<PruebasReportTo>();
        for (Test prueba : e.getTests()) {
            PruebasReportTo p = new PruebasReportTo();
            var catalog = this.catalogServiceClient.getCatalogById(prueba.getId()).block();
            p.setId(prueba.getId());
            p.setNombrePrueba(catalog.getTestName());
            p.setReferencia(catalog.getReference());
            p.setMinValue(catalog.getMinValue());
            p.setMaxValue(catalog.getMaxValue());
            p.setValor(prueba.getResult());

            pruebasList.add(p);
        }
        reporte.setExamId(e.getId());
        reporte.setPatient(patient);
        reporte.setFechaRealizada(e.getCreationDate());
        reporte.setPruebas(pruebasList);

        return reporte;
    }

    @Override
    public List<AreaTo> buscarAreasPendientes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarAreasPendientes'");
    }

}
