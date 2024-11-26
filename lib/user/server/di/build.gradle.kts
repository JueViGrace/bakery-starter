plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.user.di"

dependencies {
    // Database: server
    implementation(projects.lib.core.server.database)

    // Data: user
    implementation(projects.lib.user.server.data)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Koin
    implementation(libs.koin.core)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
