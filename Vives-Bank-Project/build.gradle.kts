plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    id("jacoco")
}

group = "org.example"
version = ""

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()

}

dependencies {
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")

    // Negociacion de contenido
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

    // Jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // PostgreSQL
    implementation ("org.postgresql:postgresql")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // MongoDB
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // Rest
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Cache
    implementation("org.springframework.boot:spring-boot-starter-cache")

    // Caffeine (Para poner un ttl y un limite a la cache)
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

    // Validación
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // websocket
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    // Lombock
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Jackson
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.1")

    // Pdf
    implementation ("com.itextpdf:itext7-core:7.2.3")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JWT (Json Web Token)
    implementation("com.auth0:java-jwt:4.4.0")

    // Redis
    implementation ("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("redis.clients:jedis:5.2.0")

    // csv
    implementation("com.opencsv:opencsv:5.7.1")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // testear MongoDB
    implementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.18.0")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.18.0")

    // Test Spring Security
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.required.set(true)
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestReport)
}