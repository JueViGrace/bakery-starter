package com.bakery.core.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIResponse<out T : Any?>(
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
)
