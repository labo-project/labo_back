package com.labo.exams.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PruebasReportTo {
    private Long id;
    private String nombrePrueba;
    private String referencia;
    private BigDecimal valorMin;
    private BigDecimal valorMax;
    private BigDecimal valor;
    private Long idArea;
    private String nombreArea;
}
