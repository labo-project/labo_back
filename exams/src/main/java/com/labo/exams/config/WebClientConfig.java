package com.labo.exams.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class WebClientConfig {

    @Value("${api.gateway.url}")
    private String apiGatewayUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(authorizationFilter());
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

    private ExchangeFilterFunction authorizationFilter() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            String token = extractTokenFromContext();
            if (token != null) {
                return Mono.just(ClientRequest.from(clientRequest)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .build());
            }
            return Mono.just(clientRequest);
        });
    }

    private String extractTokenFromContext() {
        // Try to get from SecurityContext first
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            return (String) authentication.getCredentials();
        }

        // Fallback to getting from RequestContextHolder if available
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    return authHeader.substring(7);
                }
            }
        } catch (Exception e) {
            // Handle reactive context where ServletRequestAttributes might not be available
        }

        return null;
    }
}