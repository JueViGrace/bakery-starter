package com.bakery.core.shared.types

sealed class DataError(val code: Int, val error: String? = null) {
    data class BadRequest(val message: String? = null) : DataError(
        code = 400,
        error = message
    )
    data class NotFound(val message: String? = null) : DataError(
        code = 404,
        error = message
    )
    data class ServerDataError(val message: String? = null) : DataError(
        code = 500,
        error = message
    )
    data class DatabaseDataError(val message: String? = null) : DataError(
        code = 601,
        error = message
    )
    data class NullDataError(val message: String? = null) : DataError(
        code = 602,
        error = message
    )
    data class VersionDataError(val message: String? = null) : DataError(
        code = 603,
        error = message
    )
    data class UnknownDataError(val message: String? = null) : DataError(
        code = 604,
        error = message
    )
    data class UnexpectedDataError(val message: String? = null) : DataError(
        code = 604,
        error = message
    )
}
