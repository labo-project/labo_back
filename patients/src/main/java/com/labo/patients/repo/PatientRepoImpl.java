package com.labo.patients.repo;

import org.springframework.stereotype.Repository;

import com.labo.patients.repo.model.Patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PatientRepoImpl implements IPatientRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Patient findById(Long id) {
        TypedQuery<Patient> myQuery = this.entityManager.createQuery("SELECT e FROM Patient e WHERE e.id = :id",
         Patient.class).setParameter("id", id);
        return myQuery.getSingleResult();
    }
    
}
