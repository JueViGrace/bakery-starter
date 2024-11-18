plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
        }
        jvmMain.dependencies {
        }
    }
}
