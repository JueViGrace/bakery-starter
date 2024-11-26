plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.user.data"

dependencies {
    // Types: core
    implementation(projects.lib.core.shared.types)

    // Types: server
    implementation(projects.lib.core.server.types)

    // Types: user
    implementation(projects.lib.user.shared.types)

    // Database: server
    implementation(projects.lib.core.server.database)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
