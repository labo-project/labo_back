package com.labo.exams.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExamTo {
    private Long id;
    private Long patientId;
    private Long userId;
    private LocalDateTime creationDate;
}
