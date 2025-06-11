plugins {
    kotlin("jvm") version "2.1.21"
    `maven-publish`
}

group = "com.github.moriguin"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.5")
    implementation("jakarta.validation:jakarta.validation-api:3.1.1")
    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.20")
    implementation("io.swagger.core.v3:swagger-models:2.2.32")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    sourceSets["main"].kotlin.srcDir("build/generated/src/main/kotlin")
}


publishing {
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
            artifactId = "worklog-api-schema"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/moriguin/worklog-api-schema")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}