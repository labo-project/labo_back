package com.labo.catalog.dto;

import java.util.List;

import lombok.Data;

@Data
public class CatalogAreaTo {
    private Long id;

    private String name;

    private List<CatalogTestTo> tests;
}
