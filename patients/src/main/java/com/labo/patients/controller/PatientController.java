package com.labo.patients.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.labo.patients.repo.model.Patient;
import com.labo.patients.service.IPatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Long id) {
        Patient patient = patientService.findById(id);
        return patient != null 
            ? ResponseEntity.ok(patient) 
            : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        return ResponseEntity.ok(this.patientService.findAll());
    }

    @PostMapping
    public ResponseEntity<Patient> crear(@RequestBody Patient patient) {
        this.patientService.crear(patient);
        return ResponseEntity.ok(patient);
    }
    
    @PutMapping
    public String putMethodName(@RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    
}

