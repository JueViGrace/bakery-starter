plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.bakery.core.api"

dependencies {
    // Types: core
    implementation(projects.lib.core.shared.types)

    // Types: server
    implementation(projects.lib.core.server.types)

    // Auth
    implementation(projects.lib.auth.server.routes)
    implementation(projects.lib.auth.server.validation)

    // User
    implementation(projects.lib.user.server.routes)
    implementation(projects.lib.user.server.validation)

    // Server
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.thymeleaf)
    implementation(libs.ktor.server.html.builder)
//    implementation(libs.ktor.server.sse)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.request.validation)
    implementation(libs.ktor.server.netty)

    // Koin
    implementation(libs.koin.core)

    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.core)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
