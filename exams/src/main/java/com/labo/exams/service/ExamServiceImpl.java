package com.labo.exams.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.exams.clients.CatalogClient;
import com.labo.exams.clients.PatientClient;
import com.labo.exams.dto.DatosTo;
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

        pruebasTo.setNombrePrueba(catalog.getName());
        pruebasTo.setValor(test.getResult());
        return pruebasTo;
    }

    @Override
    public List<Exam> buscarTodos() {
        return this.examRepo.buscarTodos();
    }

}
