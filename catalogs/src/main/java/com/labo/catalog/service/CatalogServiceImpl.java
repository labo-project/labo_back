package com.labo.catalog.service;

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

    

    
}
