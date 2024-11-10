package com.labo.patients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.patients.repo.IPatientRepo;
import com.labo.patients.repo.model.Patient;

@Service
public class PatientServiceImpl implements IPatientService{

    @Autowired
    private IPatientRepo patientRepo;

    @Override
    public Patient findById(Long id) {
        return this.patientRepo.findById(id);
    }
    
}
