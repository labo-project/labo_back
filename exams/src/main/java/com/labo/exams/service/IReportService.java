package com.labo.exams.service;

import com.labo.exams.dto.ExamReportTo;

public interface IReportService {
    public byte[] generateLabReport(ExamReportTo reportData);
}
