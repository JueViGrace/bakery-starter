plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.auth.validation"

dependencies {
    // Types: core
    implementation(projects.lib.core.shared.types)

    // Types: auth
    implementation(projects.lib.auth.shared.types)

    // Util: server
    implementation(projects.lib.core.server.util)

    // Server
    implementation(libs.ktor.server.request.validation)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
