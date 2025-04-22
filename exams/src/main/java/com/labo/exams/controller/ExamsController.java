package com.labo.exams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.labo.exams.dto.DatosTo;
import com.labo.exams.dto.ExamTo;
import com.labo.exams.repo.model.Exam;
import com.labo.exams.service.IExamService;

@RestController
public class ExamsController {

    @Autowired
    private IExamService examService;

    @GetMapping
    public ResponseEntity<List<DatosTo>> getAllExams() {
        List<DatosTo> exams = this.examService.showPendingExams();
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ExamTo>> todos() {     
        return ResponseEntity.ok(this.examService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<ExamTo> crearExamen(@RequestBody Exam e){
        this.examService.crearExamen(e);
        ExamTo respuesta = new ExamTo();
        respuesta.setId(e.getId());
        respuesta.setPatientId(e.getPatientId());
        respuesta.setUserId(e.getUserId());
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("completar")
    public ResponseEntity<DatosTo> completarPruebas(@RequestBody DatosTo datos) {
        this.examService.completarExamen(datos);
        return ResponseEntity.ok(datos);
    }
    

    
}