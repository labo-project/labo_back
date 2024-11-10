package com.labo.catalog.repository;

import org.springframework.stereotype.Repository;

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
    public CatalogTest findyById(Long id) {
        TypedQuery<CatalogTest> query = this.entityManager.createQuery(
        "SELECT ct FROM CatalogTest ct " +
        "LEFT JOIN FETCH ct.area " +
        "WHERE ct.id = :id", 
        CatalogTest.class
    );
    query.setParameter("id", id);
    
    try {
        return query.getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
    }

}
