plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.bakery.core.server.types"

dependencies {
    implementation(projects.lib.core.shared.types)

    // Ktor
    implementation(libs.ktor.server.core)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Kotlin Datetime
    implementation(libs.kotlinx.datetime)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
