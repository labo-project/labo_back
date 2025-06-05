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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.labo.exams.dto.AreaTo;
import com.labo.exams.dto.ExamReportTo;

@Service
public class ReportServiceImpl implements IReportService {

    public byte[] generateLabReport(ExamReportTo reportData) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph title = new Paragraph("LABORATORY REPORT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Add patient information box
            addPatientInfoBox(document, reportData);
            document.add(Chunk.NEWLINE);

            // Create test results table
            PdfPTable table = new PdfPTable(4); // 4 columns (reduced from 5)
            table.setWidthPercentage(100);

            // Set column widths
            float[] columnWidths = { 3f, 1f, 2f, 1f };
            table.setWidths(columnWidths);

            table.addCell(createHeaderCell("Nombre"));
            table.addCell(createHeaderCell("Valor"));
            table.addCell(createHeaderCell("Valores Referenciales"));
            table.addCell(createHeaderCell("Referencia"));

            for (AreaTo area : reportData.getAreas()) {
                // Create area header cell that spans all 4 columns
                PdfPCell areaHeaderCell = createHeaderCell(area.getName());
                areaHeaderCell.setColspan(4); // Span across all 4 columns
                areaHeaderCell.setBackgroundColor(BaseColor.LIGHT_GRAY); // Different color to distinguish from column
                                                                         // headers
                areaHeaderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(areaHeaderCell);

                // Add tests for this area
                for (var test : area.getTests()) {
                    // Test name
                    table.addCell(createCell(test.getName()));

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

                    // Combined min-max cell
                    String refValues = String.format("%.2f - %.2f", min, max);
                    table.addCell(createCell(refValues));

                    // Reference unit
                    table.addCell(createCell(test.getReference()));
                }
            }

            document.add(table);
            // // Add interpretation section
            // document.add(Chunk.NEWLINE);
            // document.add(new Paragraph("Interpretation:", new
            // Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

            // Check if any test is outside reference range

            // boolean hasAbnormalValues = false;
            // for (PruebasReportTo test : reportData.getPruebas()) {
            // double value = ((Number) test.getValor()).doubleValue();
            // double min = ((Number) test.getMinValue()).doubleValue();
            // double max = ((Number) test.getMaxValue()).doubleValue();

            // if (value < min || value > max) {
            // hasAbnormalValues = true;
            // String interpretation = test.getNombrePrueba() + " is " +
            // (value < min ? "below" : "above") +
            // " the reference range.";
            // document.add(new Paragraph("• " + interpretation, new
            // Font(Font.FontFamily.HELVETICA, 12)));
            // }
            // }

            // if (!hasAbnormalValues) {
            // document.add(new Paragraph("All values are within normal reference ranges.",
            // new Font(Font.FontFamily.HELVETICA, 12)));
            // }

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

    /**
     * Creates a box with patient information
     */
    private void addPatientInfoBox(Document document, ExamReportTo reportData) throws Exception {
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

        // Create a table for patient info with border
        PdfPTable patientInfoTable = new PdfPTable(2);
        patientInfoTable.setWidthPercentage(100);
        float[] columnWidths = { 1f, 3f };
        patientInfoTable.setWidths(columnWidths);

        // Add patient information rows
        addInfoRow(patientInfoTable, "Exam ID:", reportData.getExamId().toString(), boldFont, normalFont);
        addInfoRow(patientInfoTable, "Patient Name:", reportData.getPatient().getName(), boldFont, normalFont);

        // You can add more patient information fields as needed
        addInfoRow(patientInfoTable, "Patient ID:",
                reportData.getPatient().getId() != null ? reportData.getPatient().getId().toString() : "N/A", boldFont,
                normalFont);
        addInfoRow(patientInfoTable, "Edad:",
                reportData.getPatient().getEdad() != null ? reportData.getPatient().getEdad().toString() : "N/A",
                boldFont,
                normalFont);
        addInfoRow(patientInfoTable, "Collection Date:",
                reportData.getFechaRealizada() != null ? reportData.getFechaRealizada().toString() : "N/A", boldFont,
                normalFont);

        // Set border for the whole table
        patientInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
        patientInfoTable.getDefaultCell().setBorderWidth(2);

        document.add(patientInfoTable);
    }

    /**
     * Adds a row to the patient info table
     */
    private void addInfoRow(PdfPTable table, String label, String value, Font boldFont, Font normalFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, boldFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPadding(5);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, normalFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPadding(5);

        table.addCell(labelCell);
        table.addCell(valueCell);
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