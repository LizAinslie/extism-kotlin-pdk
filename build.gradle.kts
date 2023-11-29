plugins {
    kotlin("multiplatform") version "1.9.20"
}

group = "system.alpha"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    @OptIn(org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl::class)
    wasmJs {
        binaries.library()
        d8()
    }
}