package com.bakery.core.types.errors

import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.server_error
import com.bakery.core.resources.resources.generated.resources.unexpected_error
import com.bakery.core.resources.resources.generated.resources.unknown_error
import com.bakery.core.resources.resources.generated.resources.version_error
import org.jetbrains.compose.resources.StringResource

sealed class DataCodes(val code: Int, val message: StringResource, val error: String? = null) {
    data class Ok(val msg: StringResource) : DataCodes(
        code = 200,
        message = msg
    )
    data class BadRequest(
        val msg: StringResource,
        val err: String? = null
    ) : DataCodes(
        code = 400,
        message = msg,
        error = err
    )
    data class NotFound(
        val msg: StringResource,
        val err: String? = null
    ) : DataCodes(
        code = 404,
        message = msg,
        error = err
    )
    data class ServerError(
        val msg: StringResource = Res.string.server_error,
        val err: String? = null
    ) : DataCodes(
        code = 500,
        message = msg,
        error = err
    )
    data class UnexpectedError(
        val msg: StringResource = Res.string.unexpected_error,
        val err: String? = null
    ) : DataCodes(
        code = 600,
        message = msg,
        error = err
    )
    data class DatabaseError(
        val msg: StringResource,
        val err: String? = null
    ) : DataCodes(
        code = 601,
        message = msg,
        error = err
    )
    data class NullError(
        val msg: StringResource,
        val err: String? = null
    ) : DataCodes(
        code = 602,
        message = msg,
        error = err
    )
    data class VersionError(
        val msg: StringResource = Res.string.version_error,
        val err: String? = null
    ) : DataCodes(
        code = 603,
        message = msg,
        error = err
    )
    data class UnknownError(
        val msg: StringResource = Res.string.unknown_error,
        val err: String? = null
    ) : DataCodes(
        code = 604,
        message = msg,
        error = err
    )

    companion object {
        fun fromCode(code: Int): DataCodes {
            return DataCodes::class.sealedSubclasses
                .firstOrNull { it.objectInstance?.code == code }
                ?.objectInstance ?: UnknownError()
        }
    }
}
