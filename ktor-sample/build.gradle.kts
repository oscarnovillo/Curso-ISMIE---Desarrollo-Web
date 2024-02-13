
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

// Koin
val koin_ktor_version: String by project
val koin_ksp_version: String by project

val exposed_version: String by project
val h2_version: String by project
plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.8"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"

}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("mysql:mysql-connector-java:8.0.30")



    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // Koin for Dependency Injection
    implementation("io.insert-koin:koin-ktor:$koin_ktor_version") // Koit for Ktor
    implementation("io.insert-koin:koin-logger-slf4j:$koin_ktor_version") // Koin Logger
    implementation("io.insert-koin:koin-annotations:$koin_ksp_version") // Koin Annotations for KSP
    ksp("io.insert-koin:koin-ksp-compiler:$koin_ksp_version") // Koin KSP Compiler for KSP



}
