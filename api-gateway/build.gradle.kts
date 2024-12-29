
dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    configurations {
        all {
            exclude(group = "org.springframework", module = "spring-webmvc")
            exclude(module = "spring-boot-starter-web")
        }
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
    }
}