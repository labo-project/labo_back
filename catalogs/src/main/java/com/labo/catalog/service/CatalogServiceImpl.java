package com.labo.catalog.service;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.catalog.dto.CatalogAreaTo;
import com.labo.catalog.dto.CatalogTestTo;
import com.labo.catalog.dto.ReportPruebasTo;
import com.labo.catalog.repository.ICatalogRepo;
import com.labo.catalog.repository.model.CatalogArea;
import com.labo.catalog.repository.model.CatalogTest;

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
        catalog.setAreaNombre(dto.getArea().getName());
        catalog.setIdArea(dto.getArea().getId());
        catalog.setIdPrueba(id);
        catalog.setPruebaNombre(dto.getNombre());
        return catalog;
    }

    @Override
    public void insertArea(CatalogAreaTo area) {
        var s = mapper.map(area, CatalogArea.class);
        this.repo.insertArea(s);
    }

    @Override
    public void updateArea(CatalogAreaTo area) {


       this.repo.updateArea(mapper.map(area, CatalogArea.class));
    }

    @Override
    public void eliminarArea(Long id) {
        this.repo.deleteArea(id);
    }

    @Override
    public void updateTest(CatalogTestTo test) {
        this.repo.updateTest(mapper.map(test,CatalogTest.class));
    }

    @Override
    public void insertTest(CatalogTestTo test) {

        var area = this.repo.findyById(test.getAreaId());
        var prueba = mapper.map(test, CatalogTest.class);
        prueba.setArea(area);
        this.repo.insertTest(prueba);

    }

    

    
}
