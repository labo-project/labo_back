package com.labo.exams.helpers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * Helper class for creating modern, rounded table styles
 */
public class TableStyleHelper {

    private static final float CELL_PADDING = 8f;
    private static final float BORDER_WIDTH = 0.5f;

    /**
     * Applies modern styling to a table with rounded appearance
     */
    public static void applyModernTableStyle(PdfPTable table) {
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.getDefaultCell().setPadding(CELL_PADDING);
    }

    /**
     * Applies rounded table style for patient info box
     */
    public static void applyRoundedTableStyle(PdfPTable table) {
        table.setSpacingBefore(5f);
        table.setSpacingAfter(5f);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.getDefaultCell().setPadding(CELL_PADDING);
    }

    /**
     * Creates a modern header cell with gradient-like appearance
     */
    public static PdfPCell createModernHeaderCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontHelper.getTableHeaderFont()));
        
        // Modern blue gradient-like background
        cell.setBackgroundColor(FontHelper.PRIMARY_COLOR);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(CELL_PADDING);
        
        // Subtle border styling
        cell.setBorderWidth(BORDER_WIDTH);
        cell.setBorderColor(FontHelper.BORDER_COLOR);
        cell.setUseBorderPadding(true);
        
        return cell;
    }

    /**
     * Creates a modern data cell with subtle styling
     */
    public static PdfPCell createModernDataCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontHelper.getTableDataFont()));
        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(CELL_PADDING);
        
        // Subtle border
        cell.setBorderWidth(BORDER_WIDTH);
        cell.setBorderColor(FontHelper.BORDER_COLOR);
        cell.setUseBorderPadding(true);
        
        // Alternating row effect can be added here if needed
        cell.setBackgroundColor(BaseColor.WHITE);
        
        return cell;
    }

    /**
     * Creates an area header cell with modern styling
     */
    public static PdfPCell createAreaHeaderCell(String areaName) {
        PdfPCell cell = new PdfPCell(new Phrase(areaName, FontHelper.getAreaHeaderFont()));
        
        cell.setColspan(4); // Span across all columns
        cell.setBackgroundColor(FontHelper.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(CELL_PADDING);
        
        // Styling for area separation
        cell.setBorderWidth(BORDER_WIDTH);
        cell.setBorderColor(FontHelper.BORDER_COLOR);
        cell.setUseBorderPadding(true);
        
        return cell;
    }

    /**
     * Styles cells for patient information box
     */
    public static void styleInfoCell(PdfPCell cell, boolean isLabel) {
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(CELL_PADDING);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        if (isLabel) {
            cell.setBackgroundColor(FontHelper.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        } else {
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        }
        
        // Add subtle internal borders for better definition
        cell.setBorderWidthBottom(BORDER_WIDTH);
        cell.setBorderColorBottom(FontHelper.BORDER_COLOR);
    }

    /**
     * Creates a cell with rounded corners effect (simulated through styling)
     */
    public static PdfPCell createRoundedCell(String text, boolean isHeader) {
        PdfPCell cell;
        
        if (isHeader) {
            cell = new PdfPCell(new Phrase(text, FontHelper.getBoldFont()));
            cell.setBackgroundColor(FontHelper.ACCENT_COLOR);
        } else {
            cell = new PdfPCell(new Phrase(text, FontHelper.getNormalFont()));
            cell.setBackgroundColor(BaseColor.WHITE);
        }
        
        cell.setPadding(CELL_PADDING + 2); // Extra padding for rounded appearance
        cell.setBorderWidth(0); // Remove default border
        cell.setUseBorderPadding(true);
        
        return cell;
    }

    /**
     * Adds subtle shadow effect to tables (simulated through border styling)
     */
    public static void addShadowEffect(PdfPTable table) {
        table.getDefaultCell().setBorderWidth(1f);
        table.getDefaultCell().setBorderColor(new BaseColor(200, 200, 200));
        table.setSplitLate(false);
    }

    /**
     * Creates alternating row colors for better readability
     */
    public static PdfPCell createAlternatingRowCell(String text, boolean isEvenRow) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontHelper.getTableDataFont()));
        
        if (isEvenRow) {
            cell.setBackgroundColor(new BaseColor(249, 250, 251)); // Very light gray
        } else {
            cell.setBackgroundColor(BaseColor.WHITE);
        }
        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(CELL_PADDING);
        cell.setBorderWidth(BORDER_WIDTH);
        cell.setBorderColor(FontHelper.BORDER_COLOR);
        
        return cell;
    }
}