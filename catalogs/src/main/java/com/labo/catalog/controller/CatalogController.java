package com.labo.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.labo.catalog.repository.model.CatalogTest;
import com.labo.catalog.service.ICatalogService;

@RestController
public class CatalogController {
    @Autowired
    private ICatalogService catalogService;

    @GetMapping("/{id}")
    public ResponseEntity<CatalogTest> findById(@PathVariable Long id) {
        CatalogTest test = this.catalogService.findyById(id);
        return test != null 
            ? ResponseEntity.ok(test) 
            : ResponseEntity.notFound().build();
    }
}
