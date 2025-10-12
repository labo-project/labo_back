package com.labo.exams.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.labo.exams.config.ReportConfiguration;

import lombok.Data;

/**
 * Helper class for managing laboratory information displayed in reports
 */
@Component
public class LaboratoryInfoHelper {
    
    private static ReportConfiguration reportConfiguration;

    public LaboratoryInfoHelper(ReportConfiguration reportConfiguration) {
        LaboratoryInfoHelper.reportConfiguration = reportConfiguration;
    }

   

    // Fallback default values if configuration is not available
    private static final String DEFAULT_LAB_NAME = "MedLab Analytics";
    private static final String DEFAULT_LAB_ADDRESS = "123 Medical Center Drive";
    private static final String DEFAULT_LAB_CITY = "Healthcare City, HC 12345";
    private static final String DEFAULT_LAB_PHONE = "Phone: +1 (555) 123-4567";
    private static final String DEFAULT_LAB_EMAIL = "info@medlabanalytics.com";


    /**
     * Returns formatted laboratory information for display in report header
     */
    public static List<String> getLabInfo() {
        List<String> labInfo = new ArrayList<>();
        
        if (reportConfiguration != null && reportConfiguration.getInfo() != null) {
            ReportConfiguration.Info info = reportConfiguration.getInfo();
            
            labInfo.add(getValueOrDefault(info.getName(), DEFAULT_LAB_NAME));
            labInfo.add(getValueOrDefault(info.getAddress(), DEFAULT_LAB_ADDRESS));
            labInfo.add(getValueOrDefault(info.getCity(), DEFAULT_LAB_CITY));
            labInfo.add(getValueOrDefault(info.getPhone(), DEFAULT_LAB_PHONE));
            labInfo.add(getValueOrDefault(info.getEmail(), DEFAULT_LAB_EMAIL));
        } else {
            // Use default values
            labInfo.add(DEFAULT_LAB_NAME);
            labInfo.add(DEFAULT_LAB_ADDRESS);
            labInfo.add(DEFAULT_LAB_CITY);
            labInfo.add(DEFAULT_LAB_PHONE);
            labInfo.add(DEFAULT_LAB_EMAIL);
        }
        
        return labInfo;
    }

    /**
     * Returns laboratory name only
     */
    public static String getLabName() {
        if (reportConfiguration != null && reportConfiguration.getInfo() != null) {
            return getValueOrDefault(reportConfiguration.getInfo().getName(), DEFAULT_LAB_NAME);
        }
        return DEFAULT_LAB_NAME;
    }

    /**
     * Returns laboratory contact information
     */
    public static String getLabContact() {
        String phone = DEFAULT_LAB_PHONE;
        String email = DEFAULT_LAB_EMAIL;
        
        if (reportConfiguration != null && reportConfiguration.getInfo() != null) {
            phone = getValueOrDefault(reportConfiguration.getInfo().getPhone(), DEFAULT_LAB_PHONE);
            email = getValueOrDefault(reportConfiguration.getInfo().getEmail(), DEFAULT_LAB_EMAIL);
        }
        
        return phone + " | " + email;
    }

    /**
     * Returns laboratory address information
     */
    public static String getLabAddress() {
        String address = DEFAULT_LAB_ADDRESS;
        String city = DEFAULT_LAB_CITY;
        
        if (reportConfiguration != null && reportConfiguration.getInfo() != null) {
            address = getValueOrDefault(reportConfiguration.getInfo().getAddress(), DEFAULT_LAB_ADDRESS);
            city = getValueOrDefault(reportConfiguration.getInfo().getCity(), DEFAULT_LAB_CITY);
        }
        
        return address + ", " + city;
    }

    /**
     * Helper method to get value or default
     */
    private static String getValueOrDefault(String value, String defaultValue) {
        return (value != null && !value.trim().isEmpty()) ? value : defaultValue;
    }

    /**
     * Returns abbreviated lab info for compact display
     */
    public static List<String> getCompactLabInfo() {
        List<String> compactInfo = new ArrayList<>();
        
        compactInfo.add(DEFAULT_LAB_NAME);
        compactInfo.add(DEFAULT_LAB_ADDRESS);
        compactInfo.add(DEFAULT_LAB_PHONE);
        compactInfo.add(DEFAULT_LAB_EMAIL);
        
        return compactInfo;
    }

    /**
     * Custom laboratory information configuration
     * This method allows setting custom lab info from application properties
     */
    @Data
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
}