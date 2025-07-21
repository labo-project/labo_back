package com.labo.catalog.repository;

import java.util.List;

import com.labo.catalog.repository.model.CatalogArea;
import com.labo.catalog.repository.model.CatalogTest;

public interface ICatalogRepo {
    public CatalogTest findyByIdTest(Long id);
    public List<CatalogTest> findAllTest();
    public CatalogArea findyById(Long id);
    public List<CatalogArea> findAll();
    public void insertArea(CatalogArea area);
    public void updateArea(CatalogArea area);
    public void deleteArea(Long id);
}
