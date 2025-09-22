import net.researchgate.release.ReleaseExtension

plugins {
    kotlin("jvm") version "2.2.0"
    `maven-publish`
    id("net.researchgate.release") version "3.1.0"
}

group = "com.github.moriguin"
version = "${project.version}"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.4")
    implementation("jakarta.validation:jakarta.validation-api:3.1.1")
    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.2")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.37")
    implementation("io.swagger.core.v3:swagger-models:2.2.32")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()
}

kotlin {
    sourceSets["main"].kotlin.srcDir("build/generated/src/main/kotlin")
}

configure<ReleaseExtension> {
    tagTemplate = "v${project.version}"
    newVersionCommitMessage = "chore: bump version to ${project.version}"
    failOnSnapshotDependencies = false
    ignoredSnapshotDependencies.set(listOf("net.researchgate:gradle-release"))

    git {
        requireBranch.set("master")
    }
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