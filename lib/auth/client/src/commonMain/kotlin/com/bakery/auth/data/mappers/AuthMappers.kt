package com.bakery.auth.data.mappers

import com.bakery.auth.shared.types.AuthDto
import com.bakery.core.database.Bakery_session
import com.bakery.core.types.Session

private typealias BakerySession = Bakery_session

fun BakerySession.toDomain(): Session = Session(
    accessToken = access_token,
    refreshToken = refresh_token,
    userId = user_id
)

fun AuthDto.toDomain(): Session = Session(
    accessToken = accessToken,
    refreshToken = refreshToken,
)

fun Session.toDb(): BakerySession = BakerySession(
    access_token = accessToken,
    refresh_token = refreshToken,
    user_id = userId
)
