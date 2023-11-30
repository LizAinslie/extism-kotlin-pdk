plugins {
    kotlin("multiplatform") version "1.9.20"
}

group = "extism.kotlin"
version = "0.0-ci"

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