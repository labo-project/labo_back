package com.labo.catalog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.labo.catalog.dto.CatalogAreaTo;
import com.labo.catalog.dto.CatalogTestTo;
import com.labo.catalog.dto.ReportPruebasTo;
import com.labo.catalog.service.ICatalogService;



@RestController
public class CatalogController {
    @Autowired
    private ICatalogService catalogService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<CatalogAreaTo> findById(@PathVariable("id") Long id) {
        var test = this.catalogService.findAreaById(id);
        return test != null
                ? ResponseEntity.ok(mapper.map(test, CatalogAreaTo.class))
                : ResponseEntity.notFound().build();
    }

    @GetMapping("test/{id}")
    public ResponseEntity<ReportPruebasTo> findTestById(@PathVariable("id") Long id) {
        var test = this.catalogService.findTest(id);
        return test != null
                ? ResponseEntity.ok(test)
                : ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<CatalogAreaTo>> findAll() {
        var test = this.catalogService.findAll();

        return test != null
                ? ResponseEntity
                        .ok(test.stream().map(e -> mapper
                                .map(e, CatalogAreaTo.class))
                                .collect(Collectors.toList()))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CatalogTestTo> insertarTest(@RequestBody CatalogTestTo entity) {
        
        this.catalogService.insertTest(entity);
        return ResponseEntity.ok(entity);
    }
    
    @PutMapping
    public ResponseEntity<CatalogTestTo> actualizarTest(@RequestBody CatalogTestTo entity) {
        
        this.catalogService.updateTest(entity);
        return ResponseEntity.ok(entity);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarArea(@PathVariable Long id) {
        
        this.catalogService.eliminarArea(id);
        return ResponseEntity.ok("Area eliminada");
        
    }
}
