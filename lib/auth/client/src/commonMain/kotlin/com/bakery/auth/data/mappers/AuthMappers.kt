package com.bakery.auth.data.mappers

import com.bakery.auth.shared.types.AuthDto
import com.bakery.core.database.Bakery_session
import com.bakery.core.database.FindActiveAccount
import com.bakery.core.types.Session
import com.bakery.core.types.User

private typealias BakerySession = Bakery_session

fun FindActiveAccount.toDomain(): Session = Session(
    accessToken = access_token,
    refreshToken = refresh_token,
    userId = user_id,
    user = User(
        id = id ?: "",
        firstName = first_name ?: "",
        lastName = last_name ?: "",
        username = username ?: "",
        email = email ?: "",
        phoneNumber = phone_number ?: "",
        birthDate = birth_date ?: "",
        address1 = address1 ?: "",
        address2 = address2 ?: "",
        gender = gender ?: "",
        role = role ?: "",
        createdAt = created_at ?: "",
    )
)

fun AuthDto.toDomain(): Session = Session(
    accessToken = accessToken,
    refreshToken = refreshToken,
)

fun Session.toDb(): BakerySession = BakerySession(
    access_token = accessToken,
    refresh_token = refreshToken,
    user_id = userId,
    active = active
)
