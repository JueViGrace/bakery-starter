plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.user.validation"

dependencies {
    // Types: core
    implementation(projects.lib.core.shared.types)

    // Types: server
    implementation(projects.lib.core.server.types)

    // Types: user
    implementation(projects.lib.user.shared.types)

    // Util: Server
    implementation(projects.lib.core.server.util)

    // Server
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.request.validation)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
