plugins {
    id("buildlogic.java-application-conventions")
    id("org.springframework.boot") version "3.2.3" 
    id("io.spring.dependency-management") version "1.1.6" 
    kotlin("jvm") version "2.0.21" 
    kotlin("plugin.spring") version "2.0.21" 
    kotlin("plugin.jpa") version "2.0.21" 
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    
    // Lombok dependencies
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
    }
}
