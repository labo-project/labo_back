package com.labo.exams.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CatalogTo {
    private Long idPrueba;
    private String testName;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String reference;
    private Long idArea;
    private String areaName;
}

