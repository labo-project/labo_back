package com.labo.exams.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.labo.exams.repo.model.Exam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ExamRepoImpl implements IExamRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Exam> buscarTodos() {
        TypedQuery<Exam> myQuery = this.entityManager.createQuery("SELECT e FROM Exam e", Exam.class);
        return myQuery.getResultList();
    }
    
}
