package com.labo.catalog.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CatalogTestTo {
    
    private Long id;
    private String nombre;
    private BigDecimal valorMin;
    private BigDecimal valorMax;
    private String referencia;
    private Long areaId;
}
