package com.labo.exams.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.labo.exams.repo.model.Exam;
import com.labo.exams.repo.model.Test;

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

    @Override
    public void createExam(Exam e) {
        e.setStatus(false);
        for (Test prueba : e.getTests()) {
            prueba.setResult(null);
            prueba.setCompletionDate(null);
            prueba.setExam(e);
        }
        this.entityManager.persist(e);
    }

    @Override
    public void createTest(Test t) {
        this.entityManager.persist(t);
    }
    
}
