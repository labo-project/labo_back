package com.labo.exams.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCatalogTo {
    private Long id;
    private String nombre;
    private BigDecimal valorMin;
    private BigDecimal valorMax;
    private String referencia;
    private BigDecimal valor;
}
