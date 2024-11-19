package com.bakery.server.config

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt

fun Application.configureSecurity() {
    // todo: create jwt logic

    install(Authentication) {
        jwt("session-auth") {
        }
        jwt("role-auth") {
        }
    }
}
