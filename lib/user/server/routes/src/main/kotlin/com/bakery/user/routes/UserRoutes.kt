package com.bakery.user.routes

import com.bakery.core.types.ServerResponse.badRequest
import com.bakery.core.types.applicationResponse
import com.bakery.user.data.handler.UserHandler
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val handler: UserHandler by inject<UserHandler>()

    route("/users") {
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

            call.applicationResponse(
                response = response,
                onSuccess = { res ->
                    call.respond(
                        status = HttpStatusCode(
                            value = res.status,
                            description = res.description
                        ),
                        message = res
                    )
                },
                onFailure = { res ->
                    call.respond(
                        status = HttpStatusCode(
                            value = res.status,
                            description = res.description
                        ),
                        message = res
                    )
                }
            )
        }

        get("{id}") {
            val idParam = call.parameters["id"]
            if (idParam == null) {
                return@get call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = badRequest(
                        message = "Invalid request"
                    )
                )
            }

            val response = handler.getUserById(idParam)

            call.applicationResponse(
                response = response,
                onSuccess = { res ->
                    call.respond(
                        status = HttpStatusCode(
                            value = res.status,
                            description = res.description
                        ),
                        message = res
                    )
                },
                onFailure = { res ->
                    call.respond(
                        status = HttpStatusCode(
                            value = res.status,
                            description = res.description
                        ),
                        message = res
                    )
                }
            )
        }
    }
}
