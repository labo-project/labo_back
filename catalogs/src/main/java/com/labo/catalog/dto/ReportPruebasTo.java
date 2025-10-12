package com.labo.catalog.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ReportPruebasTo {
    private Long idPrueba;
    private String pruebaNombre;
    private BigDecimal valorMin;
    private BigDecimal valorMax;
    private String referencia;
    private Long idArea;
    private String areaNombre;
}
