package com.labo.exams.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ExamReportTo {

    private Long examId;
    private PatientTo patient;
    private LocalDateTime fechaRealizada;
    private List<PruebasReportTo> pruebas;

}
