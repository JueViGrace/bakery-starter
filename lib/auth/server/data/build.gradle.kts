plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.auth.server.data"

dependencies {
    implementation(projects.lib.core.shared.types)
    implementation(projects.lib.core.server.types)

    implementation(projects.lib.core.shared.database)
    implementation(projects.lib.core.server.database)

    implementation(projects.lib.auth.shared.types)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
