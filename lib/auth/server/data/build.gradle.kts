plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.auth.data"

dependencies {
    // Types: core
    implementation(projects.lib.core.shared.types)

    // Types: server
    implementation(projects.lib.core.server.types)

    // Database: server
    implementation(projects.lib.core.server.database)

    // Types: auth
    implementation(projects.lib.auth.shared.types)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
