package com.labo.catalog.service;

import java.util.List;

import com.labo.catalog.repository.model.CatalogTest;

public interface ICatalogService {
    public CatalogTest findyById(Long id);
    public List<CatalogTest> findAll();
}
