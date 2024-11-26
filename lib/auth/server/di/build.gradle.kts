plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.auth.di"

dependencies {
    // Database: server
    implementation(projects.lib.core.server.database)

    // Data: auth
    implementation(projects.lib.auth.server.data)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Koin
    implementation(libs.koin.core)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
