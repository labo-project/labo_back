package com.labo.exams.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCatalogTo {
    private String name;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String reference;
    private BigDecimal valor;
}
