// Root project build.gradle.kts
plugins {
    java
    id("buildlogic.java-application-conventions")
    id("org.springframework.boot") version "3.4.5" 
    id("io.spring.dependency-management") version "1.1.7" 
    kotlin("jvm") version "2.0.21" 
    kotlin("plugin.spring") version "2.0.21" 
    kotlin("plugin.jpa") version "2.0.21" 
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    // https://mvnrepository.com/artifact/com.auth0/java-jwt
    implementation("com.auth0:java-jwt:4.4.0")

    
    // Add these
    // implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
    // implementation("io.jsonwebtoken:jjwt-jackson:0.12.6")

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
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Java Mapper
    implementation("net.sf.dozer:dozer:5.5.1")
    
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
    doFirst {
        println("Compiling with args: ${options.compilerArgs}")
    }
}

tasks.test {
    useJUnitPlatform()
}