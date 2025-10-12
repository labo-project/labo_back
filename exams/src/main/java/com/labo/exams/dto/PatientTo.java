package com.labo.exams.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PatientTo {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private Integer edad;
    private LocalDateTime fechaCreacion;
}
