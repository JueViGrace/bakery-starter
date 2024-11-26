plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.user.validation"

dependencies {
    // Types: core
    implementation(projects.lib.core.shared.types)

    // Types: user
    implementation(projects.lib.user.shared.types)

    implementation(libs.ktor.server.request.validation)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
