// Root project build.gradle.kts
plugins {
    id("buildlogic.java-application-conventions")
    id("org.springframework.boot") version "3.4.5" 
    id("io.spring.dependency-management") version "1.1.7" 
    kotlin("jvm") version "2.0.21" 
    kotlin("plugin.spring") version "2.0.21" 
    kotlin("plugin.jpa") version "2.0.21" 
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    
    // Lombok dependencies
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Java Mapper
    implementation("net.sf.dozer:dozer:5.5.1")

    implementation("org.springframework.boot:spring-boot-starter-webflux:3.3.5")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // libreria para realizar los reportes en PDF
    implementation("com.itextpdf:itextpdf:5.5.13.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    implementation("io.projectreactor:reactor-core")

    
    
}
