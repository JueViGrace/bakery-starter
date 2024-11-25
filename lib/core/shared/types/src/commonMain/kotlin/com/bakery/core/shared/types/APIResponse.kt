package com.bakery.core.shared.types

import kotlinx.serialization.Serializable

@Serializable
data class APIResponse<T>(
    val status: Int,
    val description: String,
    val data: T,
    val message: String? = null,
    val time: String = Constants.currentTime
)
