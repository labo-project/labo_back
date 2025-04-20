package com.labo.exams.service;

import java.util.List;

import com.labo.exams.dto.DatosTo;
import com.labo.exams.repo.model.Exam;

public interface IExamService {
    public List<DatosTo> showPendingExams();
    public List<Exam> buscarTodos();
    public void completarExamen(DatosTo datos);
    public void crearExamen(Exam e);
}
