package com.labo.patients.service;

import com.labo.patients.repo.model.Patient;

public interface IPatientService {
    public Patient findById(Long id);
}
