plugins {
    id("java")
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

dependencies {
    // Spring Cloud Gateway (core requirement)
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    
    // If you need service discovery (optional)
    // implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    
    // Only add these if you actually need them:
    // implementation("com.fasterxml.jackson.core:jackson-databind") // Already included by Spring Boot
    // implementation("org.springframework.boot:spring-boot-starter-webflux") // Included by gateway
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2024.0.0")
    }
}