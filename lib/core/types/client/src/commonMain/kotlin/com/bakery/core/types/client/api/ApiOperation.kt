package com.bakery.core.types.client.api

import com.bakery.core.types.shared.DataError

// todo: improve this
sealed interface ApiOperation<out T> {
    data class Success<T>(val data: T) : ApiOperation<T>
    data class Failure(val error: DataError) : ApiOperation<Nothing>
}
