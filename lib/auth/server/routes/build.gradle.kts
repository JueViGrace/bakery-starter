plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.auth.server.routes"

dependencies {
    implementation(projects.lib.core.shared.types)
    implementation(projects.lib.core.server.types)

    implementation(projects.lib.auth.shared.types)
    implementation(projects.lib.auth.server.data)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Server
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
