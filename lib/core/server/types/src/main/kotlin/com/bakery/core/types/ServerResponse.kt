package com.bakery.core.types

import com.bakery.core.shared.types.APIResponse
import com.bakery.core.shared.types.Constants
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApplicationResponse<out T> {
    @Serializable
    data class Success<T>(
        @SerialName("status")
        val status: Int,
        @SerialName("description")
        val description: String,
        @SerialName("data")
        val data: T?,
        @SerialName("message")
        val message: String? = null,
        @SerialName("time")
        val time: String = Constants.currentTime
    ) : ApplicationResponse<T>()

    @Serializable
    data class Failure<Nothing>(
        @SerialName("status")
        val status: Int,
        @SerialName("description")
        val description: String,
        @SerialName("data")
        val data: Nothing? = null,
        @SerialName("message")
        val message: String? = null,
        @SerialName("time")
        val time: String = Constants.currentTime
    ) : ApplicationResponse<Nothing>()
}

object ServerResponse {
    inline fun <reified T : Any?>ok(data: T?, message: String? = null): ApplicationResponse<T> {
        val response = APIResponse(
            status = HttpStatusCode.OK.value,
            description = HttpStatusCode.OK.description,
            data = data,
            message = message
        )
        return ApplicationResponse.Success(
            status = response.status,
            description = response.description,
            data = response.data,
            message = response.message,
        )
    }

    inline fun <reified T : Any?>created(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.Created.value,
            description = HttpStatusCode.Created.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>accepted(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.Accepted.value,
            description = HttpStatusCode.Accepted.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>noContent(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.NoContent.value,
            description = HttpStatusCode.NoContent.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>badRequest(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.BadRequest.value,
            description = HttpStatusCode.BadRequest.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>unauthorized(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.Unauthorized.value,
            description = HttpStatusCode.Unauthorized.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>forbidden(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.Forbidden.value,
            description = HttpStatusCode.Forbidden.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>notFound(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.NotFound.value,
            description = HttpStatusCode.NotFound.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>methodNotAllowed(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.MethodNotAllowed.value,
            description = HttpStatusCode.MethodNotAllowed.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>notAcceptable(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.NotAcceptable.value,
            description = HttpStatusCode.NotAcceptable.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>requestTimeout(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.RequestTimeout.value,
            description = HttpStatusCode.RequestTimeout.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>conflict(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.Conflict.value,
            description = HttpStatusCode.Conflict.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>unsupportedMediaType(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.UnsupportedMediaType.value,
            description = HttpStatusCode.UnsupportedMediaType.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>internalServerError(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.InternalServerError.value,
            description = HttpStatusCode.InternalServerError.description,
            data = data,
            message = message
        )
    }

    inline fun <reified T : Any?>serviceUnavailable(data: T?, message: String? = null): APIResponse<T> {
        return APIResponse(
            status = HttpStatusCode.ServiceUnavailable.value,
            description = HttpStatusCode.ServiceUnavailable.description,
            data = data,
            message = message
        )
    }
}
