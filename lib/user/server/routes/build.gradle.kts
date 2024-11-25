plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.user.server.routes"

dependencies {
    implementation(projects.lib.core.shared.types)
    implementation(projects.lib.core.server.types)

    implementation(projects.lib.user.shared.types)
    implementation(projects.lib.user.server.data)

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
