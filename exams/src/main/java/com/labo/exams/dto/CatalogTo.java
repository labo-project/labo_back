package com.labo.exams.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CatalogTo {
    private Long idPrueba;
    private String pruebaNombre;
    private BigDecimal valorMin;
    private BigDecimal valorMax;
    private String referencia;
    private Long idArea;
    private String areaNombre;
}

