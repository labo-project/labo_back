package com.labo.patients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labo.patients.repo.model.Patient;
import com.labo.patients.service.IPatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        return patient != null 
            ? ResponseEntity.ok(patient) 
            : ResponseEntity.notFound().build();
    }
}

