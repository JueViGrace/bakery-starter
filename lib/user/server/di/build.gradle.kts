plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.user.server.di"

dependencies {
    implementation(projects.lib.core.shared.di)
    implementation(projects.lib.core.shared.database)
    implementation(projects.lib.core.server.database)

    implementation(projects.lib.user.server.data)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Koin
    implementation(libs.koin.core)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
