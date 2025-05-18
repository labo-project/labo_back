package com.labo.patients.service;

import java.util.List;

import com.labo.patients.repo.model.Patient;

public interface IPatientService {
    public Patient findById(Long id);
    public List<Patient> findAll();
    public void crear(Patient p);
    public void update(Patient p);
}
