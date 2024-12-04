package com.bakery.core.types

data class Session(
    val accessToken: String,
    val refreshToken: String,
    val userId: String = "",
    val active: Boolean = false,
    val user: User? = null
)
