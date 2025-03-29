plugins {
    kotlin("jvm")
}

group = "br.com.thalesnishida.syncbar.application"
version = "0.0.1-SNAPSHOT"


repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    testImplementation(kotlin("test"))
    implementation("io.vavr:vavr:1.0.0-alpha-4")
    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("io.arrow-kt:arrow-core:1.2.0")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf("-Xmx2g")
}
kotlin {
    jvmToolchain(17)
}