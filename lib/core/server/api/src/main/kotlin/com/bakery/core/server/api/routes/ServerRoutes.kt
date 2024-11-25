package com.bakery.core.server.api.routes

import com.bakery.auth.server.routes.authRoutes
import com.bakery.user.server.routes.userRoutes
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

fun Routing.serverRoutes() {
    apiRoutes()
    webRoutes()
}

fun Routing.apiRoutes() {
    get("/health") {
        call.respond(
            status = HttpStatusCode.OK,
            message = "Health is ok!"
        )
    }

    authRoutes()
    userRoutes()
}

fun Routing.webRoutes() {
    get {
        call.respond(
            status = HttpStatusCode.OK,
            message = "Root"
        )
    }
}
