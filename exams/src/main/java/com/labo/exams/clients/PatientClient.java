package com.labo.exams.clients;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.labo.exams.dto.PatientTo;

import reactor.core.publisher.Mono;
import java.util.List;

@Component
public class PatientClient {
    private final WebClient patientServiceClient;

    public PatientClient(WebClient patientServiceClient) {
        this.patientServiceClient = patientServiceClient;
    }

    public Mono<PatientTo> getPatientById(Long id) {
        return patientServiceClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(PatientTo.class);
    }

    public Mono<List<PatientTo>> getAllPatients() {
        return patientServiceClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(PatientTo.class)
                .collectList();
    }
}