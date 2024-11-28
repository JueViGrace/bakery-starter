package com.bakery.user.routes

import com.bakery.core.types.JwtAuthName
import com.bakery.core.types.applicationResponse
import com.bakery.user.data.handler.UserHandler
import com.bakery.user.shared.types.UpdateUserDto
import com.bakery.user.shared.types.UserByIdDto
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.AuthenticationStrategy
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val handler: UserHandler by inject<UserHandler>()

    route("/users") {
        authenticate(JwtAuthName.SESSION.value, strategy = AuthenticationStrategy.Required) {
            // Admin user auth
            authenticate(JwtAuthName.ADMIN.value, strategy = AuthenticationStrategy.Required) {
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

                post {
                    val body = call.receive<UserByIdDto>()
                    val response = handler.getUserById(body.id)

                    call.applicationResponse(
                        response = response,
                        onFailure = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        },
                        onSuccess = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        }
                    )
                }

                delete {
                    val body = call.receive<UserByIdDto>()
                    val response = handler.deleteUser(body.id)

                    call.applicationResponse(
                        response = response,
                        onFailure = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        },
                        onSuccess = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        }
                    )
                }
            }

            // Normal user auth
            authenticate(JwtAuthName.USER.value, strategy = AuthenticationStrategy.Required) {
                post {
                    val body = call.receive<UserByIdDto>()
                    val response = handler.getExistingUserById(body.id)

                    call.applicationResponse(
                        response = response,
                        onFailure = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        },
                        onSuccess = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        }
                    )
                }

                // todo: routes for changing email and username
                patch {
                    val body = call.receive<UpdateUserDto>()
                    val response = handler.updateUser(body)

                    call.applicationResponse(
                        response = response,
                        onFailure = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        },
                        onSuccess = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        }
                    )
                }

                // todo: route to recover user after soft delete
                delete {
                    val body = call.receive<UserByIdDto>()
                    val response = handler.softDeleteUser(body.id)

                    call.applicationResponse(
                        response = response,
                        onFailure = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        },
                        onSuccess = { res ->
                            call.respond(
                                status = HttpStatusCode(
                                    value = res.status,
                                    description = res.description,
                                ),
                                message = res
                            )
                        }
                    )
                }
            }
        }
    }
}
