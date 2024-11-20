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

//    linuxX64()
//    mingwX64()

    sourceSets {
//        val desktopMain by creating

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

        // Uncomment if using Pg database in server
//        desktopMain.dependencies {
//            // Coroutines
//            implementation(libs.kotlinx.coroutines.swing)
//
//            // Sqlite
//            implementation(libs.sqldelight.sqlite.driver)
//            implementation(libs.sqlite)
//        }

        jvmMain.dependencies {
            // Coroutines
            implementation(libs.kotlinx.coroutines.swing)

            // Sqlite
            implementation(libs.sqldelight.sqlite.driver)
            implementation(libs.sqlite)

            // Postgres
            // implementation(libs.sqldelight.postgres.driver)
            // implementation(libs.postgresql)
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
        create("BakerySlDb") {
            packageName.set("com.bakery.core.database")
            dialect(libs.sqldelight.sqlite.dialect)
            generateAsync.set(true)
        }
        // If server needs Pg database uncomment
//        create("BakeryPgDb") {
//            packageName.set("com.bakery.core.database")
//            dialect(libs.sqldelight.postgres.dialect)
//            generateAsync.set(true)
//        }
    }
}
