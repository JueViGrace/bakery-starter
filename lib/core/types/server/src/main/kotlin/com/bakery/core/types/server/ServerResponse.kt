package com.bakery.core.types.server

import com.bakery.core.types.shared.APIResponse
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
        return APIResponse<T>(HttpStatusCode.Accepted.value, HttpStatusCode.Accepted.description, data, message)
    }

    inline fun <reified T> Ok(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.OK.value,
            description = HttpStatusCode.OK.description,
            data = data,
            message = message
        )
    }
    inline fun <reified T> Ok(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.OK.value,
            description = HttpStatusCode.OK.description,
            data = data,
            message = message
        )
    }
    inline fun <reified T> Ok(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.OK.value,
            description = HttpStatusCode.OK.description,
            data = data,
            message = message
        )
    }
    inline fun <reified T> Ok(data: T, message: String? = null): APIResponse<T> {
        return APIResponse<T>(
            status = HttpStatusCode.OK.value,
            description = HttpStatusCode.OK.description,
            data = data,
            message = message
        )
    }
}
