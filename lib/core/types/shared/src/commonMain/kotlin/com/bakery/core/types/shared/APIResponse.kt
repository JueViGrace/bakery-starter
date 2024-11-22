package com.bakery.core.types.shared

import kotlinx.serialization.Serializable

@Serializable
data class APIResponse<T>(
    val status: Int,
    val description: String,
    val data: T,
    val message: String? = null,
    val time: String = Constants.currentTime
)
