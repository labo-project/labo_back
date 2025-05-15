package com.labo.exams.service;

import java.util.List;

import com.labo.exams.dto.AreaTo;
import com.labo.exams.dto.DatosTo;
import com.labo.exams.dto.ExamReportTo;
import com.labo.exams.dto.ExamTo;
import com.labo.exams.repo.model.Exam;

public interface IExamService {
    public List<DatosTo> showPendingExams(Long areaId);
    public List<ExamTo> buscarTodos();
    public void completarExamen(DatosTo datos);
    public void crearExamen(Exam e);
    public ExamReportTo buscarReportId(Long id);
    public List<AreaTo> buscarAreasPendientes();
}
