package com.labo.patients.repo;

import java.util.List;

import com.labo.patients.repo.model.Patient;

public interface IPatientRepo {
    public Patient findById(Long id);
    public List<Patient> findAll();
    public void crear(Patient p);
    public void update(Patient p);
}
