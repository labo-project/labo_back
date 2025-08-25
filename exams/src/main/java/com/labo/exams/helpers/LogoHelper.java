package com.labo.exams.helpers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;

/**
 * Helper class for handling logo operations in PDF reports
 */
@Component
public class LogoHelper {

    /**
     * Loads and returns a logo image from the specified path
     * 
     * @param logoPath Path to the logo image
     * @param maxWidth Maximum width for the logo
     * @param maxHeight Maximum height for the logo
     * @return Image object or null if loading fails
     */
    public static Image loadLogo(String logoPath, float maxWidth, float maxHeight) {
        try {
            // Try to load from classpath first
            if (logoPath.startsWith("classpath:")) {
                String path = logoPath.substring("classpath:".length());
                Resource resource = new ClassPathResource(path);
                
                if (resource.exists()) {
                    try (InputStream inputStream = resource.getInputStream()) {
                        byte[] imageBytes = inputStream.readAllBytes();
                        Image logo = Image.getInstance(imageBytes);
                        logo.scaleToFit(maxWidth, maxHeight);
                        return logo;
                    }
                }
            } else {
                // Try to load from file system
                Image logo = Image.getInstance(logoPath);
                logo.scaleToFit(maxWidth, maxHeight);
                return logo;
            }
        } catch (BadElementException | IOException e) {
            // Log the error if needed
            System.err.println("Failed to load logo from path: " + logoPath + " - " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Creates a logo cell for the PDF header
     * 
     * @param logoPath Path to the logo image
     * @param maxWidth Maximum width for the logo
     * @param maxHeight Maximum height for the logo
     * @return PdfPCell containing the logo or placeholder
     */
    public static PdfPCell createLogoCell(String logoPath, float maxWidth, float maxHeight) {
        PdfPCell logoCell = new PdfPCell();
        logoCell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        logoCell.setPadding(10);
        logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Image logo = loadLogo(logoPath, maxWidth, maxHeight);
        
        if (logo != null) {
            logoCell.addElement(logo);
        } else {
            // Create a styled placeholder if logo can't be loaded
            Paragraph logoPlaceholder = createLogoPlaceholder();
            logoCell.addElement(logoPlaceholder);
        }

        return logoCell;
    }

    /**
     * Creates a styled logo placeholder
     */
    private static Paragraph createLogoPlaceholder() {
        Paragraph placeholder = new Paragraph("LOGO", FontHelper.getHeaderFont());
        placeholder.setAlignment(Element.ALIGN_CENTER);
        
        // You could add more styling here, like a border or background color
        return placeholder;
    }

    /**
     * Creates a more elaborate logo placeholder with company name
     */
    public static Paragraph createCompanyLogoPlaceholder(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            companyName = "LABORATORY";
        }
        
        Paragraph placeholder = new Paragraph(companyName.toUpperCase(), FontHelper.getHeaderFont());
        placeholder.setAlignment(Element.ALIGN_CENTER);
        
        return placeholder;
    }

    /**
     * Validates if the logo path points to a valid image file
     */
    public static boolean isValidLogoPath(String logoPath) {
        if (logoPath == null || logoPath.trim().isEmpty()) {
            return false;
        }

        try {
            if (logoPath.startsWith("classpath:")) {
                String path = logoPath.substring("classpath:".length());
                Resource resource = new ClassPathResource(path);
                return resource.exists();
            } else {
                // For file system paths, you might want to add additional validation
                return logoPath.toLowerCase().matches(".*\\.(png|jpg|jpeg|gif|bmp)$");
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the default logo path from configuration or returns a fallback
     */
    public static String getDefaultLogoPath() {
        return "classpath:static/images/logo.png";
    }

    /**
     * Creates a logo cell with automatic fallback to company name
     */
    public static PdfPCell createLogoCell(String logoPath, String companyName, float maxWidth, float maxHeight) {
        PdfPCell logoCell = new PdfPCell();
        logoCell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        logoCell.setPadding(10);
        logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        // Try to load the actual logo
        Image logo = loadLogo(logoPath, maxWidth, maxHeight);
        
        if (logo != null) {
            logoCell.addElement(logo);
        } else {
            // Use company name as a styled placeholder
            Paragraph logoPlaceholder = createCompanyLogoPlaceholder(companyName);
            logoCell.addElement(logoPlaceholder);
        }

        return logoCell;
    }
}