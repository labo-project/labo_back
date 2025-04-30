package com.labo.catalog.service;

import java.util.List;

import com.labo.catalog.repository.model.CatalogArea;

public interface ICatalogService {
    public CatalogArea findyById(Long id);
    public List<CatalogArea> findAll();
}
