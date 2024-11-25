plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.bakery.user.server.validation"

dependencies {
    implementation(projects.lib.core.shared.types)
    implementation(projects.lib.user.shared.types)

    implementation(libs.ktor.server.request.validation)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
