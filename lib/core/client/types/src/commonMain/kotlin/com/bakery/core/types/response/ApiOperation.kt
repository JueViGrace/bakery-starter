package com.bakery.core.types.response

import com.bakery.core.types.errors.DataCodes

sealed interface ApiOperation<out T> {
    data class Success<T>(val value: APIResponse<T>) : ApiOperation<T>
    data class Failure(val error: Int) : ApiOperation<Nothing>
}

inline fun <reified T> ApiOperation<T>.display(
    onSuccess: (APIResponse<T>) -> Unit,
    onFailure: (DataCodes) -> Unit
) {
    when (this) {
        is ApiOperation.Success -> onSuccess(value)
        is ApiOperation.Failure -> onFailure(DataCodes.fromCode(error))
    }
}
