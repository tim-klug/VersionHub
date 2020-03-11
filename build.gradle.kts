import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.gradle.dsl.Coroutines

val kotlinVersion = "1.3.70"
val ktorVersion = "1.0.0"
val exposedVersion = "0.22.1"

plugins {
    kotlin("jvm") version "1.3.70"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    application
}

repositories {
    jcenter()
    mavenCentral()
    "https://dl.bintray.com/kotlin".let {
        maven { setUrl("$it/ktor") }
        maven { setUrl("$it/kotlinx") }
    }
}

application {
    mainClassName = "VersionHub.AppKt"
}

dependencies {
    fun ktor(s: String = "", v: String = ktorVersion) = "io.ktor:ktor$s:$v"

    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.10")
    implementation(ktor())
    implementation(ktor("-gson"))
    implementation(ktor("-html-builder"))
    implementation(ktor("-server-netty"))
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.exposed", "exposed-core", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-dao", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)
    implementation("com.h2database", "h2", "1.4.200")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}
