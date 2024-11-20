package com.bakery.server.config

import com.bakery.core.api.server.userRoutes
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRoutes() {
    routing {
        apiRoutes()
        webRoutes()
    }
}

fun Routing.apiRoutes() {
    get("/api/health") {
        call.respond(
            status = HttpStatusCode.OK,
            message = "Health is ok!"
        )
    }

    userRoutes()
}

fun Routing.webRoutes() {
}
