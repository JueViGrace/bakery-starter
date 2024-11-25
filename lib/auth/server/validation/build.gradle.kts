plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.auth.server.validation"

dependencies {
    implementation(projects.lib.core.shared.types)
    implementation(projects.lib.auth.shared.types)

    implementation(libs.ktor.server.request.validation)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
