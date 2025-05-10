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

}
