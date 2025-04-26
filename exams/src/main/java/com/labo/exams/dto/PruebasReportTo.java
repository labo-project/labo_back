package com.labo.exams.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PruebasReportTo {
    private Long id;
    private String nombrePrueba;
    private String referencia;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private BigDecimal valor;
}
