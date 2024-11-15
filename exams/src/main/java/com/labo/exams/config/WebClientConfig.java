package com.labo.exams.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Value("${api.gateway.url:http://localhost:8080}")
    private String apiGatewayUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient patientServiceClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(apiGatewayUrl + "/patients")
                .build();
    }

    @Bean
    public WebClient catalogServiceClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(apiGatewayUrl + "/catalogs")
                .build();
    }
}