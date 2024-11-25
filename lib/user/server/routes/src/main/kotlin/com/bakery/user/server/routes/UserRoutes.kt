package com.bakery.user.server.routes

import com.bakery.core.server.types.applicationResponse
import com.bakery.user.server.data.handler.UserHandler
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val handler: UserHandler by inject<UserHandler>()

    route("/api/users") {
        /*authenticate("user-auth", strategy = AuthenticationStrategy.Required) {
            authenticate("admin-auth", strategy = AuthenticationStrategy.Required) {
                get {
                }

                delete {
                }
            }

            // todo: make this another authentication strategy?
            get("/{id}") {
            }

            patch {
            }

            delete {
            }
        }*/

        get {
            val response = handler.getUsers()

            call.applicationResponse(response)
        }
    }
}
