package com.labo.exams.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.labo.exams.dto.CatalogTo;

import reactor.core.publisher.Mono;

@Component
public class CatalogClient {
    
    @Autowired
    private WebClient catalogServiceClient;

    public Mono<CatalogTo> getCatalogById(Long id) {
        return catalogServiceClient.get()
                .uri("/test/{id}", id)
                .retrieve()
                .bodyToMono(CatalogTo.class);
    }

    public Mono<List<CatalogTo>> getAllCatalogs() {
        return catalogServiceClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(CatalogTo.class)
                .collectList();
    }
}
