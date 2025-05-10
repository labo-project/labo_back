package com.labo.catalog.service;

import java.util.List;

import com.labo.catalog.dto.ReportPruebasTo;
import com.labo.catalog.repository.model.CatalogArea;

public interface ICatalogService {
    public CatalogArea findAreaById(Long id);
    public List<CatalogArea> findAll();
    public ReportPruebasTo findTest(Long id);
}
