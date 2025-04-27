package com.labo.exams.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.labo.exams.dto.ExamReportTo;
import com.labo.exams.dto.PruebasReportTo;

@Service
public class ReportServiceImpl implements IReportService {

   public byte[] generateLabReport(ExamReportTo reportData) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();
            
            // Add title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph title = new Paragraph("LABORATORY REPORT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);
            
            // Add patient information
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            
            document.add(new Paragraph("Exam ID: " + reportData.getExamId(), normalFont));
            document.add(new Paragraph("Patient: " + reportData.getPatientApellido(), normalFont));
            document.add(Chunk.NEWLINE);
            
            // Create test results table
            PdfPTable table = new PdfPTable(5); // 5 columns
            table.setWidthPercentage(100);
            
            // Set column widths
            float[] columnWidths = {3f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            
            // Add table headers
            table.addCell(createHeaderCell("Test Name"));
            table.addCell(createHeaderCell("Unit"));
            table.addCell(createHeaderCell("Min"));
            table.addCell(createHeaderCell("Max"));
            table.addCell(createHeaderCell("Result"));
            


            
            for (PruebasReportTo test : reportData.getPruebas()) {
                table.addCell(createCell(test.getNombrePrueba()));
                table.addCell(createCell(test.getReferencia()));
                table.addCell(createCell(String.format("%.2f", test.getMinValue())));
                table.addCell(createCell(String.format("%.2f", test.getMaxValue())));
                
                // Format the value and check if it's outside reference range
                double value = ((Number) test.getValor()).doubleValue();
                double min = ((Number) test.getMinValue()).doubleValue();
                double max = ((Number) test.getMaxValue()).doubleValue();
                
                PdfPCell valueCell = createCell(String.format("%.2f", value));
                
                // Highlight abnormal values
                if (value < min || value > max) {
                    valueCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                }
                
                table.addCell(valueCell);
            }
            
            document.add(table);
            
            // Add interpretation section
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Interpretation:", boldFont));
            
            // Check if any test is outside reference range
            boolean hasAbnormalValues = false;
            for (PruebasReportTo test : reportData.getPruebas()) {
                double value = ((Number) test.getValor()).doubleValue();
                double min = ((Number) test.getMinValue()).doubleValue();
                double max = ((Number) test.getMaxValue()).doubleValue();
                
                if (value < min || value > max) {
                    hasAbnormalValues = true;
                    String interpretation = test.getNombrePrueba() + " is " + 
                                          (value < min ? "below" : "above") + 
                                          " the reference range.";
                    document.add(new Paragraph("• " + interpretation, normalFont));
                }
            }
            
            if (!hasAbnormalValues) {
                document.add(new Paragraph("All values are within normal reference ranges.", normalFont));
            }
            
            // Add report footer
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            Paragraph footer = new Paragraph("Report generated on: " + new java.util.Date(), 
                                            new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC));
            footer.setAlignment(Element.ALIGN_RIGHT);
            document.add(footer);
            
            document.close();
            return baos.toByteArray();
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report", e);
        }
    }
    
    private PdfPCell createHeaderCell(String text) {
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        return cell;
    }
    
    private PdfPCell createCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        return cell;
    }

}
