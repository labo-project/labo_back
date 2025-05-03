package com.labo.exams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.labo.exams.dto.DatosTo;
import com.labo.exams.dto.ExamReportTo;
import com.labo.exams.dto.ExamTo;
import com.labo.exams.repo.model.Exam;
import com.labo.exams.service.IExamService;
import com.labo.exams.service.IReportService;

@RestController
public class ExamsController {

    @Autowired
    private  IExamService examService;

    @Autowired
    private  IReportService reportService;

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
    public ResponseEntity<ExamTo> crearExamen(@RequestBody Exam e) {
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

    @GetMapping("report/{id}")
    public ResponseEntity<ExamReportTo> getMethodName(@PathVariable("id") Long id) {

        return ResponseEntity.ok(this.examService.buscarReportId(id));
    }

    @PostMapping("generateReport/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable("id") Long id) {
        byte[] pdfBytes = this.reportService.generateLabReport(this.examService.buscarReportId(id));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "lab-report.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}