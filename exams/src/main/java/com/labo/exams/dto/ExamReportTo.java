package com.labo.exams.dto;

import java.util.List;

import lombok.Data;

@Data
public class ExamReportTo {

    private Long examId;
    private String patientApellido;
    private List<PruebasReportTo> pruebas;

}
