package com.labo.exams.repo;

import java.util.List;

import com.labo.exams.repo.model.Exam;
import com.labo.exams.repo.model.Test;

public interface IExamRepo {
    public List<Exam> buscarTodos();
    public void createExam(Exam e);
    public void createTest(Test t);
}
