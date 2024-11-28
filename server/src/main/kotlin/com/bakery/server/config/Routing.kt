package com.bakery.server.config

import com.bakery.core.types.ServerResponse.badRequest
import com.bakery.core.types.ServerResponse.internalServerError
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.path
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import org.slf4j.LoggerFactory

fun Application.configureRouting() {
    val logger = LoggerFactory.getLogger("Routing")

    install(StatusPages) {
        exception<Throwable> { call: ApplicationCall, cause: Throwable ->
            logger.error("Unhandled error at: ${call.request.path()}", cause)
            call.respond(
                status = HttpStatusCode.InternalServerError,
                message = internalServerError(
                    message = "Something unexpected happened, try again later."
                )
            )
        }

        exception<RequestValidationException> { call, cause ->
            logger.error("Validation error", cause)
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = badRequest(
                    message = "Invalid request."
                )
            )
        }
    }

//    install(SSE)

    routing {
        staticResources("/static", "static")
    }
}
