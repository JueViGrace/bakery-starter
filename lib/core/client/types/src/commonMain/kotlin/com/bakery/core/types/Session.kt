package com.bakery.core.types

data class Session(
    val accessToken: String,
    val refreshToken: String,
    val user: User,
    val active: Boolean = false,
)
