package com.labo.catalog.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CatalogTestTo {
    
    private Long id;
    private String name;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String reference;
}
