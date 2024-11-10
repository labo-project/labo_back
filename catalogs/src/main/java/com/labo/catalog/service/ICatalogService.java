package com.labo.catalog.service;

import com.labo.catalog.repository.model.CatalogTest;

public interface ICatalogService {
    public CatalogTest findyById(Long id);
}
