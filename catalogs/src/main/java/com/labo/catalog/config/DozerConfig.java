package com.labo.catalog.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {
    @Bean
    Mapper dozerMapper() {
        return new DozerBeanMapper();
    }
}
