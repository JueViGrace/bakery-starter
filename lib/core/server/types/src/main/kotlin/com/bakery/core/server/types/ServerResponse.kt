package com.bakery.core.server.types

import com.bakery.core.shared.types.APIResponse
import io.ktor.http.HttpStatusCode

object ServerResponse {
    inline fun <reified T> ok(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.OK.value,
            description = HttpStatusCode.OK.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> created(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.Created.value,
            description = HttpStatusCode.Created.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> accepted(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.Accepted.value,
            description = HttpStatusCode.Accepted.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> noContent(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.NoContent.value,
            description = HttpStatusCode.NoContent.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> badRequest(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.BadRequest.value,
            description = HttpStatusCode.BadRequest.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> unauthorized(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.Unauthorized.value,
            description = HttpStatusCode.Unauthorized.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> forbidden(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.Forbidden.value,
            description = HttpStatusCode.Forbidden.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> notFound(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.NotFound.value,
            description = HttpStatusCode.NotFound.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> methodNotAllowed(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.MethodNotAllowed.value,
            description = HttpStatusCode.MethodNotAllowed.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> notAcceptable(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.NotAcceptable.value,
            description = HttpStatusCode.NotAcceptable.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> requestTimeout(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.RequestTimeout.value,
            description = HttpStatusCode.RequestTimeout.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> conflict(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.Conflict.value,
            description = HttpStatusCode.Conflict.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> unsupportedMediaType(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.UnsupportedMediaType.value,
            description = HttpStatusCode.UnsupportedMediaType.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> internalServerError(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.InternalServerError.value,
            description = HttpStatusCode.InternalServerError.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T> serviceUnavailable(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.ServiceUnavailable.value,
            description = HttpStatusCode.ServiceUnavailable.description,
            data = data,
            message = message
        )
    }
}
