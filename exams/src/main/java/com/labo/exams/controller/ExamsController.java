package com.labo.exams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.labo.exams.dto.DatosTo;
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
    public ResponseEntity<List<Exam>> todos() {
        List<Exam> exams = this.examService.buscarTodos();
        return ResponseEntity.ok(exams);
    }

    @PostMapping
    public ResponseEntity<Exam> crearExamen(@RequestBody Exam e){
        this.examService.crearExamen(e);
        return ResponseEntity.ok(e);
    }

    
}