package com.labo.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.catalog.repository.ICatalogRepo;
import com.labo.catalog.repository.model.CatalogTest;

@Service
public class CatalogServiceImpl implements ICatalogService{

    @Autowired
    private ICatalogRepo repo;

    @Override
    public CatalogTest findyById(Long id) {
       return this.repo.findyById(id);
    }

    @Override
    public List<CatalogTest> findAll() {
        return this.repo.findAll();
    }

    

    
}
