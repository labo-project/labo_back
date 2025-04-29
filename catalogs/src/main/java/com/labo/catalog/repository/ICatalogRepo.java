package com.labo.catalog.repository;

import java.util.List;

import com.labo.catalog.repository.model.CatalogTest;

public interface ICatalogRepo {
    public CatalogTest findyById(Long id);
    public List<CatalogTest> findAll();
}
