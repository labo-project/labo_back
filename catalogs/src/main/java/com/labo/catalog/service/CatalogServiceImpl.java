package com.labo.catalog.service;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.catalog.dto.CatalogTestTo;
import com.labo.catalog.dto.ReportPruebasTo;
import com.labo.catalog.repository.ICatalogRepo;
import com.labo.catalog.repository.model.CatalogArea;

@Service
public class CatalogServiceImpl implements ICatalogService{

    @Autowired
    private Mapper mapper;

    @Autowired
    private ICatalogRepo repo;

    @Override
    public CatalogArea findAreaById(Long id) {
       return this.repo.findyById(id);
    }

    @Override
    public List<CatalogArea> findAll() {
        return this.repo.findAll();
    }

    @Override
    public ReportPruebasTo findTest(Long id) {
        var dto = this.repo.findyByIdTest(id);
        var catalog = mapper.map(dto, ReportPruebasTo.class);
        catalog.setAreaName(dto.getArea().getName());
        catalog.setIdArea(dto.getArea().getId());
        catalog.setIdPrueba(id);
        catalog.setTestName(dto.getName());
        return catalog;
    }

    

    
}
