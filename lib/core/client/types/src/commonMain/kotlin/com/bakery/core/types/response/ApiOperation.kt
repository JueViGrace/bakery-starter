package com.bakery.core.types.response

import com.bakery.core.types.errors.DataError

sealed interface ApiOperation<out T> {
    data class Success<T>(val data: APIResponse<T>) : ApiOperation<T>
    data class Failure(val error: DataError) : ApiOperation<Nothing>
}
