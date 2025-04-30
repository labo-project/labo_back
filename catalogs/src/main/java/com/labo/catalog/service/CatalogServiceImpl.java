package com.labo.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.catalog.repository.ICatalogRepo;
import com.labo.catalog.repository.model.CatalogArea;

@Service
public class CatalogServiceImpl implements ICatalogService{

    @Autowired
    private ICatalogRepo repo;

    @Override
    public CatalogArea findyById(Long id) {
       return this.repo.findyById(id);
    }

    @Override
    public List<CatalogArea> findAll() {
        return this.repo.findAll();
    }

    

    
}
