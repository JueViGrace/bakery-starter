plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.auth.routes"

dependencies {
    // Types: core
    implementation(projects.lib.core.shared.types)

    // Types: server
    implementation(projects.lib.core.server.types)

    // Types: auth
    implementation(projects.lib.auth.shared.types)

    // Data: auth
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
