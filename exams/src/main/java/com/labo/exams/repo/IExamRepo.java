package com.labo.exams.repo;

import java.util.List;

import com.labo.exams.repo.model.Exam;

public interface IExamRepo {
    public List<Exam> buscarTodos();
}
