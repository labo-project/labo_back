package com.labo.exams.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.labo.exams.dto.AreaTo;
import com.labo.exams.dto.ExamReportTo;
import com.labo.exams.helpers.FontHelper;
import com.labo.exams.helpers.LaboratoryInfoHelper;
import com.labo.exams.helpers.LogoHelper;
import com.labo.exams.helpers.TableStyleHelper;

@Service
public class ReportServiceImpl implements IReportService {

    private static final String DEFAULT_LOGO_PATH = "classpath:static/images/logo.png"; // Adjust path as needed

    public byte[] generateLabReport(ExamReportTo reportData) {
        return generateLabReport(reportData, null);
    }

    public byte[] generateLabReport(ExamReportTo reportData, String logoPath) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add header with logo and lab info
            addHeader(document, logoPath != null ? logoPath : DEFAULT_LOGO_PATH);
            document.add(Chunk.NEWLINE);

            // Add title
            Paragraph title = new Paragraph("LABORATORY REPORT", FontHelper.getTitleFont());
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Add patient information box
            addPatientInfoBox(document, reportData);
            document.add(Chunk.NEWLINE);

            // Create test results table
            createTestResultsTable(document, reportData);

            // Add report footer
            addFooter(document);

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report", e);
        }
    }

    /**
     * Adds header with logo and laboratory information
     */
    private void addHeader(Document document, String logoPath) throws DocumentException, IOException {
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new float[] { 1f, 1f });

        // Left cell - Logo using LogoHelper
        PdfPCell logoCell = LogoHelper.createLogoCell(
                logoPath,
                LaboratoryInfoHelper.getLabName(),
                80f,
                80f);

        // Right cell - Laboratory info
        PdfPCell labInfoCell = new PdfPCell();
        labInfoCell.setBorder(Rectangle.NO_BORDER);
        labInfoCell.setPadding(10);
        labInfoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        // Add lab information
        for (String infoLine : LaboratoryInfoHelper.getLabInfo()) {
            Paragraph info = new Paragraph(infoLine, FontHelper.getSmallFont());
            info.setAlignment(Element.ALIGN_RIGHT);
            labInfoCell.addElement(info);
        }

        headerTable.addCell(logoCell);
        headerTable.addCell(labInfoCell);

        document.add(headerTable);
    }

    private void addPatientInfoBox(Document document, ExamReportTo reportData) throws DocumentException {
        // Create a table for patient info with 4 columns
        PdfPTable patientInfoTable = new PdfPTable(4);
        patientInfoTable.setWidthPercentage(100);
        patientInfoTable.setWidths(new float[] { 1f, 2f, 1f, 2f });

        // Style the table with rounded appearance
        TableStyleHelper.applyRoundedTableStyle(patientInfoTable);

        // Add information in 2x2 layout
        addInfoRowPair(patientInfoTable,
                "Exam ID:", reportData.getExamId().toString(),
                "Nombre del Paciente:", reportData.getPatient().getName() + " " + reportData.getPatient().getApellido());

        addInfoRowPair(patientInfoTable,
                "Cédula Paciente:",
                reportData.getPatient().getId() != null
                        ? reportData.getPatient().getCedula().toString()
                        : "N/A",
                "Edad:",
                reportData.getPatient().getEdad() != null ? reportData.getPatient().getEdad().toString() : "N/A");

        // For the last row with only one pair, pass empty strings for the second pair
        addInfoRowPair(patientInfoTable,
                "Fecha de Recolección de Muestra:",
                reportData.getFechaRealizada() != null ? reportData.getFechaRealizada().toLocalDate().toString() : "N/A",
                "ID Paciente", reportData.getPatient().getId().toString());

        document.add(patientInfoTable);
    }

    // Helper method to add a pair of label-data combinations in one row
    private void addInfoRowPair(PdfPTable table, String label1, String value1, String label2, String value2) {
        // First pair (label1, value1)
        PdfPCell labelCell1 = new PdfPCell(new Phrase(label1, FontHelper.getBoldFont()));
        TableStyleHelper.styleInfoCell(labelCell1, true);
        table.addCell(labelCell1);

        PdfPCell valueCell1 = new PdfPCell(new Phrase(value1, FontHelper.getNormalFont()));
        TableStyleHelper.styleInfoCell(valueCell1, false);
        table.addCell(valueCell1);

        // Second pair (label2, value2)
        if (!label2.isEmpty()) {
            PdfPCell labelCell2 = new PdfPCell(new Phrase(label2, FontHelper.getBoldFont()));
            TableStyleHelper.styleInfoCell(labelCell2, true);
            table.addCell(labelCell2);

            PdfPCell valueCell2 = new PdfPCell(new Phrase(value2, FontHelper.getNormalFont()));
            TableStyleHelper.styleInfoCell(valueCell2, false);
            table.addCell(valueCell2);
        } else {
            // Add empty cells if no second pair
            table.addCell(new PdfPCell(new Phrase("")));
            table.addCell(new PdfPCell(new Phrase("")));
        }
    }

    /**
     * Creates the test results table with modern styling
     */
    private void createTestResultsTable(Document document, ExamReportTo reportData) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[] { 3f, 1f, 2f, 1f });

        // Apply modern table styling
        TableStyleHelper.applyModernTableStyle(table);

        // Add headers
        table.addCell(TableStyleHelper.createModernHeaderCell("Prueba"));
        table.addCell(TableStyleHelper.createModernHeaderCell("Valor"));
        table.addCell(TableStyleHelper.createModernHeaderCell("Referencia"));
        table.addCell(TableStyleHelper.createModernHeaderCell("Unidad"));

        for (AreaTo area : reportData.getAreas()) {
            // Create area header cell that spans all 4 columns
            PdfPCell areaHeaderCell = TableStyleHelper.createAreaHeaderCell(area.getName());
            table.addCell(areaHeaderCell);

            // Add tests for this area
            for (var test : area.getTests()) {
                // Test name
                table.addCell(TableStyleHelper.createModernDataCell(test.getName()));

                // Format the value and check if it's outside reference range
                double value = test.getValor() == null ? 0 :((Number) test.getValor()).doubleValue();
                double min = ((Number) test.getMinValue()).doubleValue();
                double max = ((Number) test.getMaxValue()).doubleValue();

                PdfPCell valueCell = TableStyleHelper.createModernDataCell(String.format("%.2f", value));

                // Highlight abnormal values with a subtle color
                if (value < min || value > max) {
                    valueCell.setBackgroundColor(new BaseColor(255, 240, 240)); // Light red
                }
                table.addCell(valueCell);

                // Combined min-max cell
                String refValues = String.format("%.2f - %.2f", min, max);
                table.addCell(TableStyleHelper.createModernDataCell(refValues));

                // Reference unit
                table.addCell(TableStyleHelper.createModernDataCell(test.getReference()));
            }
        }

        document.add(table);
    }

    /**
     * Adds modern footer to the document
     */
    private void addFooter(Document document) throws DocumentException {
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        Paragraph footer = new Paragraph("Report generated on: " + new java.util.Date(),
                FontHelper.getItalicSmallFont());
        footer.setAlignment(Element.ALIGN_RIGHT);
        document.add(footer);
    }
}