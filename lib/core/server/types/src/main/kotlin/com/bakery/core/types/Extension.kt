package com.bakery.core.types

import com.bakery.core.shared.types.APIResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

suspend inline fun <reified T> ApplicationCall.applicationResponse(response: APIResponse<T>) {
    respond(
        status = HttpStatusCode(
            value = response.status,
            description = response.description
        ),
        message = response,
    )
}
