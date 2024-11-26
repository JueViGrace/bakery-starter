plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.user.routes"

dependencies {
    // Types: core
    implementation(projects.lib.core.shared.types)

    // Types: server
    implementation(projects.lib.core.server.types)

    // Types: user
    implementation(projects.lib.user.shared.types)

    // Database: server
    implementation(projects.lib.core.server.database)

    // Data: user
    implementation(projects.lib.user.server.data)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Server
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
