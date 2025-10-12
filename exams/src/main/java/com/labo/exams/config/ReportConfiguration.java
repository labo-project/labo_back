package com.labo.exams.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration class for laboratory report settings
 */
@Configuration
@ConfigurationProperties(prefix = "lab")
public class ReportConfiguration {

    private final Info info = new Info();
    private final Logo logo = new Logo();
    private final Certification certification = new Certification();
    private final Contact contact = new Contact();

    // Getters
    public Info getInfo() {
        return info;
    }

    public Logo getLogo() {
        return logo;
    }

    public Certification getCertification() {
        return certification;
    }

    public Contact getContact() {
        return contact;
    }

    /**
     * Laboratory information configuration
     */
    @Data
    public static class Info {
        private String name;
        private String address;
        private String city;
        private String phone;
        private String email;
        private String license;
        private String website;
    }

    /**
     * Logo configuration
     */
    @Data
    public static class Logo {
        private String path;
        private int width;
        private int height;
    }

    /**
     * Certification information configuration
     */
    @Data
    public static class Certification {
        private String cap;
        private String clia;
        private String iso;
        private String qa;
    }

    /**
     * Contact information configuration
     */
    @Data
    public static class Contact {
        private String emergency;
        private String hours;
    }
}