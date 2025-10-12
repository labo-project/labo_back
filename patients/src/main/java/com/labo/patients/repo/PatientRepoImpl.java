package com.labo.patients.repo;

import java.util.List;

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
        return this.entityManager.find(Patient.class, id);
    }

    @Override
    public List<Patient> findAll() {
        TypedQuery<Patient> myQuery = this.entityManager
            .createQuery("SELECT e FROM Patient e", Patient.class);
        return myQuery.getResultList();
    }

    @Override
    public void crear(Patient p) {
        this.entityManager.persist(p);
    }

    @Override
    public void update(Patient p) {
        this.entityManager.merge(p);
    }
    
}
