package com.labo.exams.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.exams.clients.CatalogClient;
import com.labo.exams.clients.PatientClient;
import com.labo.exams.dto.DatosTo;
import com.labo.exams.dto.ExamTo;
import com.labo.exams.dto.PatientTo;
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
    public List<DatosTo> showPendingExams() {
        List<Exam> exams = this.examRepo.buscarTodos();
        return exams.stream()
                .filter(exam -> exam.getTests() != null && !exam.getTests().isEmpty())
                .map(this::mapToDataTo)
                .collect(Collectors.toList());
    }

    private DatosTo mapToDataTo(Exam exam) {

        DatosTo datosTo = new DatosTo();
        datosTo.setIdExamen(exam.getId());

        Mono<PatientTo> patientMono = patientServiceClient.getPatientById(exam.getPatientId());
        PatientTo patient = patientMono.block();
        datosTo.setApellido(patient.getApellido());

        datosTo.setEstado(exam.getStatus());
        datosTo.setPruebas(exam.getTests().stream()
                .map(this::mapToPruebasTo)
                .collect(Collectors.toList()));
        return datosTo;
    }

    private PruebasTo mapToPruebasTo(Test test) {
        PruebasTo pruebasTo = new PruebasTo();
        var catalog = this.catalogServiceClient.getCatalogById(test.getTestId()).block();
        pruebasTo.setId(test.getTestId());
        pruebasTo.setNombrePrueba(catalog.getName());
        pruebasTo.setValor(null);
        return pruebasTo;
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

}
