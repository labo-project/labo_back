package com.labo.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.labexams.security", "com.labo.catalog"})
public class CatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class,args);
    }
}