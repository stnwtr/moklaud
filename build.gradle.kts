plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "at.stnwtr"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Javalin webserver
    implementation("io.javalin:javalin:3.10.1")

    // SLF4J logger
    implementation("org.slf4j:slf4j-simple:1.7.30")

    // Jackson json object mapper
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application.mainClassName = "at.stnwtr.moklaud.Launcher"
