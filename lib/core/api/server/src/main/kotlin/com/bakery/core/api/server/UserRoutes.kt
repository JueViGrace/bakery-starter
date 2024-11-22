package com.bakery.core.api.server

import com.bakery.core.types.server.ServerResponse.accepted
import com.bakery.core.types.server.ServerResponse.created
import com.bakery.core.types.server.ServerResponse.noContent
import com.bakery.core.types.server.ServerResponse.ok
import com.bakery.core.types.server.applicationResponse
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.userRoutes() {
    route("/user") {
        get("/") {
            call.applicationResponse(
                ok(
                    data = "Hello World",
                )
            )
        }

        // todo: get id from body
        post {
            call.applicationResponse(
                ok(
                    data = "This gets a user by id",
                )
            )
        }

        // todo: extact body from request
        post("/create") {
            call.applicationResponse(
                created(
                    data = "This creates a user"
                )
            )
        }

        patch("/update") {
            call.applicationResponse(
                accepted(
                    data = "This updates a user"
                )
            )
        }

        // todo: get id from body
        delete("/delete") {
            call.applicationResponse(
                noContent(
                    data = "This deletes a user"
                )
            )
        }
    }
}
