import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.7.21"
    kotlin("jvm") version "1.7.21"
    kotlin("kapt") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
}

group = "com.dsumtsov"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    mavenLocal()
}

val mapstructVersion = "1.5.3.Final"
val commonsVersion = "1.0-SNAPSHOT"
val kotlinLoggingVersion = "2.1.23"
val testContainersVersion = "1.15.3"
val jacksonDatatypeVersion = "2.7.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    implementation("com.dsumtsov:commons:$commonsVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonDatatypeVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers:$testContainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    testImplementation("org.testcontainers:mongodb:$testContainersVersion")

    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
}

noArg {
    annotation("com.dsumtsov.commons.annotation.NoArg")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
