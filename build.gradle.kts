// Root project build.gradle.kts
plugins {
    id("buildlogic.java-application-conventions") apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    kotlin("jvm") version "2.0.21" apply false
    kotlin("plugin.spring") version "2.0.21" apply false
    kotlin("plugin.jpa") version "2.0.21" apply false
}


allprojects {
    group = "com.labexams"
    version = "0.0.1-SNAPSHOT"
    
    repositories {
        mavenCentral()
    }
}