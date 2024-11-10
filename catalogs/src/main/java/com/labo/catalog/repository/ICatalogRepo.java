package com.labo.catalog.repository;

import com.labo.catalog.repository.model.CatalogTest;

public interface ICatalogRepo {
    public CatalogTest findyById(Long id);
}
