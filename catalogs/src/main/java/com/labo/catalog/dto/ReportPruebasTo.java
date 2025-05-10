package com.labo.catalog.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ReportPruebasTo {
    private Long idPrueba;
    private String testName;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String reference;
    private Long idArea;
    private String areaName;
}
