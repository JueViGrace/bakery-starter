plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.sqldelight)
}

kotlin {
    jvm("server")

    sourceSets {
        val serverMain by getting

        commonMain.dependencies {
            implementation(projects.lib.core.database.shared)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Sqldelight
            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.sqldelight.async.extensions)
        }

        serverMain.dependencies {
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

sqldelight {
    databases {
        create("BakerySvDb") {
            packageName.set("com.bakery.core.database.server")
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
