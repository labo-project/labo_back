package com.labo.exams.helpers;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Helper class for managing laboratory information displayed in reports
 */
@Data
public class LaboratoryInfoHelper {
    
    // Laboratory configuration - can be moved to properties file
    private static final String LAB_NAME = "MedLab Analytics";
    private static final String LAB_ADDRESS = "123 Medical Center Drive";
    private static final String LAB_CITY = "Healthcare City, HC 12345";
    private static final String LAB_PHONE = "Phone: +1 (555) 123-4567";
    private static final String LAB_EMAIL = "info@medlabanalytics.com";
    private static final String LAB_LICENSE = "License: ML-2024-001";
    private static final String LAB_WEBSITE = "www.medlabanalytics.com";

    /**
     * Returns formatted laboratory information for display in report header
     */
    public static List<String> getLabInfo() {
        List<String> labInfo = new ArrayList<>();
        
        labInfo.add(LAB_NAME);
        labInfo.add(LAB_ADDRESS);
        labInfo.add(LAB_CITY);
        labInfo.add(LAB_PHONE);
        labInfo.add(LAB_EMAIL);
        labInfo.add(LAB_LICENSE);
        labInfo.add(LAB_WEBSITE);
        
        return labInfo;
    }

    /**
     * Returns laboratory name only
     */
    public static String getLabName() {
        return LAB_NAME;
    }

    /**
     * Returns laboratory contact information
     */
    public static String getLabContact() {
        return LAB_PHONE + " | " + LAB_EMAIL;
    }

    /**
     * Returns laboratory address information
     */
    public static String getLabAddress() {
        return LAB_ADDRESS + ", " + LAB_CITY;
    }

    /**
     * Returns abbreviated lab info for compact display
     */
    public static List<String> getCompactLabInfo() {
        List<String> compactInfo = new ArrayList<>();
        
        compactInfo.add(LAB_NAME);
        compactInfo.add(LAB_ADDRESS);
        compactInfo.add(LAB_PHONE);
        compactInfo.add(LAB_EMAIL);
        
        return compactInfo;
    }

    /**
     * Custom laboratory information configuration
     * This method allows setting custom lab info from application properties
     */
    public static class LabInfoBuilder {
        private String name;
        private String address;
        private String city;
        private String phone;
        private String email;
        private String license;
        private String website;

        public List<String> build() {
            List<String> customInfo = new ArrayList<>();
            
            if (name != null) customInfo.add(name);
            if (address != null) customInfo.add(address);
            if (city != null) customInfo.add(city);
            if (phone != null) customInfo.add(phone);
            if (email != null) customInfo.add(email);
            if (license != null) customInfo.add(license);
            if (website != null) customInfo.add(website);
            
            return customInfo;
        }
    }

    /**
     * Creates a builder for custom laboratory information
     */
    public static LabInfoBuilder builder() {
        return new LabInfoBuilder();
    }

    /**
     * Returns laboratory certification and quality information
     */
    public static List<String> getCertificationInfo() {
        List<String> certInfo = new ArrayList<>();
        
        certInfo.add("CAP Accredited Laboratory");
        certInfo.add("CLIA Certified");
        certInfo.add("ISO 15189:2012 Compliant");
        certInfo.add("Quality Assurance Program");
        
        return certInfo;
    }

    /**
     * Returns emergency contact information
     */
    public static String getEmergencyContact() {
        return "24/7 Emergency Line: +1 (555) 911-LABS";
    }

    /**
     * Returns business hours
     */
    public static String getBusinessHours() {
        return "Hours: Mon-Fri 6:00 AM - 10:00 PM | Sat-Sun 8:00 AM - 6:00 PM";
    }
}