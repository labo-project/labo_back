package com.labo.exams.dto;

import java.util.List;

import lombok.Data;


@Data
public class DatosTo {
    
    private Long idExamen;
    private String apellido;
    private Boolean estado;
    private List<PruebasTo> pruebas;


}
