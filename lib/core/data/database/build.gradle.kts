import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
            // Sqldelight
            implementation(libs.sqldelight.android.driver)
        }

        commonMain.dependencies {
            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Sqldelight
            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.sqldelight.async.extensions)
        }

        jvmMain.dependencies {
            // Coroutines
            implementation(libs.kotlinx.coroutines.swing)

            // Sqldelight
            implementation(libs.sqldelight.sqlite.driver)

            // Sqlite
            implementation(libs.sqlite)
        }
    }
}

android {
    namespace = "com.bakery.core.database"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

sqldelight {
    databases {
        create("CloDb") {
            packageName.set("com.bakery.core.database")
            dialect(libs.sqldelight.dialect)
            generateAsync.set(true)
        }
    }
}
