package com.labo.exams.helpers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * Helper class for managing modern fonts in PDF reports
 */
public class FontHelper {

    // Modern color scheme
    public static final BaseColor PRIMARY_COLOR = new BaseColor(45, 55, 72);      // Dark blue-gray
    public static final BaseColor SECONDARY_COLOR = new BaseColor(74, 85, 104);   // Medium blue-gray
    public static final BaseColor ACCENT_COLOR = new BaseColor(49, 130, 206);     // Blue
    public static final BaseColor TEXT_COLOR = new BaseColor(45, 55, 72);         // Dark text
    public static final BaseColor LIGHT_GRAY = new BaseColor(247, 250, 252);      // Very light gray
    public static final BaseColor BORDER_COLOR = new BaseColor(226, 232, 240);    // Light border

    /**
     * Title font for main headings
     */
    public static Font getTitleFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        font.setColor(PRIMARY_COLOR);
        return font;
    }

    /**
     * Header font for section headers
     */
    public static Font getHeaderFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        font.setColor(PRIMARY_COLOR);
        return font;
    }

    /**
     * Bold font for labels and emphasis
     */
    public static Font getBoldFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        font.setColor(TEXT_COLOR);
        return font;
    }

    /**
     * Normal font for regular text
     */
    public static Font getNormalFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL);
        font.setColor(TEXT_COLOR);
        return font;
    }

    /**
     * Small font for secondary information
     */
    public static Font getSmallFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
        font.setColor(SECONDARY_COLOR);
        return font;
    }

    /**
     * Small italic font for footer and notes
     */
    public static Font getItalicSmallFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC);
        font.setColor(SECONDARY_COLOR);
        return font;
    }

    /**
     * Table header font
     */
    public static Font getTableHeaderFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        font.setColor(BaseColor.WHITE);
        return font;
    }

    /**
     * Table data font
     */
    public static Font getTableDataFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
        font.setColor(TEXT_COLOR);
        return font;
    }

    /**
     * Area header font for test categories
     */
    public static Font getAreaHeaderFont() {
        Font font = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        font.setColor(PRIMARY_COLOR);
        return font;
    }
}