package com.bakery.core.api.server

import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.userRoutes() {
    route("/user") {
        get("/") {
        }
        get("/") {
        }
        post {
        }
        patch {
        }
        delete {
        }
    }
}
