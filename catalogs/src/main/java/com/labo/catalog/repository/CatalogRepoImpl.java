package com.labo.catalog.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.labo.catalog.repository.model.CatalogArea;
import com.labo.catalog.repository.model.CatalogTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CatalogRepoImpl implements ICatalogRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CatalogTest findyByIdTest(Long id) {
        try {
            return this.entityManager.find(CatalogTest.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<CatalogTest> findAllTest() {
        TypedQuery<CatalogTest> query = this.entityManager.createQuery(
                "SELECT ct FROM CatalogTest ct " +
                        "LEFT JOIN FETCH ct.area ",
                CatalogTest.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public CatalogArea findyById(Long id) {

        try {
            return this.entityManager.find(CatalogArea.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<CatalogArea> findAll() {
        TypedQuery<CatalogArea> query = this.entityManager.createQuery(
                "SELECT ct FROM CatalogArea ct ",
                CatalogArea.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public void insertArea(CatalogArea area) {
        this.entityManager.persist(area);
    }

    @Override
    public void updateArea(CatalogArea area) {
        this.entityManager.merge(area);
        
    }

    @Override
    public void deleteArea(Long id) {
        this.entityManager.remove(this.findyById(id));
    }

    @Override
    public void updateTest(CatalogTest test) {
       var existingTest = this.entityManager.find(CatalogTest.class, test.getId());
       existingTest.setNombre(test.getNombre());
       existingTest.setValorMin(test.getValorMin());
       existingTest.setValorMax(test.getValorMax());
       existingTest.setReferencia(test.getReferencia());
    }

    @Override
    public void insertTest(CatalogTest test) {
        this.entityManager.persist(test);
    }

 

    

}
