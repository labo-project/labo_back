package com.labo.exams.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PruebasTo {
    private String nombrePrueba;
    private BigDecimal valor;
    private List<PruebasTo> pruebas;
}
