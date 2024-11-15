// Root project build.gradle.kts
plugins {
    id("buildlogic.java-application-conventions")
    id("org.springframework.boot") version "3.2.3" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    kotlin("jvm") version "2.0.21" apply false
    kotlin("plugin.spring") version "2.0.21" apply false
    kotlin("plugin.jpa") version "2.0.21" apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "com.labexams"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter:3.2.3")
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
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    // Configure Java compilation
    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }

    // Configure Kotlin compilation
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "21"
        }
    }

    // extra["springCloudVersion"] = "2023.0.0"
    
    // dependencyManagement {
    //     imports {
    //         mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    //     }
    // }
}

// // Add Spring Cloud dependency management to all projects
// subprojects {
    // extra["springCloudVersion"] = "2023.0.0"
    
    // dependencyManagement {
    //     imports {
    //         mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    //     }
    // }
// }