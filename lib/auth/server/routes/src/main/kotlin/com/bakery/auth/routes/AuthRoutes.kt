package com.bakery.auth.routes

import com.bakery.auth.data.handler.AuthHandler
import com.bakery.auth.shared.types.ForgotPasswordDto
import com.bakery.auth.shared.types.RefreshTokenDto
import com.bakery.auth.shared.types.SignInDto
import com.bakery.auth.shared.types.SignUpDto
import com.bakery.core.types.ServerResponse
import com.bakery.core.types.applicationResponse
import io.ktor.http.Cookie
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    val handler: AuthHandler by inject<AuthHandler>()

    route("/auth") {
        // todo: set refresh cookie
        post("/signIn") {
            val body = call.receive<SignInDto>()
            val response = handler.signIn(body)

            call.applicationResponse(response)
        }

        post("/signUp") {
            val body = call.receive<SignUpDto>()
            val response = handler.signUp(body)

            call.applicationResponse(response)
        }

        post("/forgotPassword") {
            val body = call.receive<ForgotPasswordDto>()
            val response = handler.forgotPassword(body)

            call.applicationResponse(response)
        }

        post("/refresh") {
            val body = call.receive<RefreshTokenDto>()
            val response = handler.refresh(body)

            response.data?.refreshToken?.let { value -> call.response.cookies.append(Cookie("refreshToken", value)) }
            call.applicationResponse(response)
        }
    }
}
