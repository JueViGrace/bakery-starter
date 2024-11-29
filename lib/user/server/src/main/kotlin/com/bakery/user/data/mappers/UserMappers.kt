package com.bakery.user.data.mappers

import com.bakery.user.shared.types.UserDto
import com.bakery.core.database.Bakery_user as BakeryUser

fun BakeryUser.toDto(): UserDto = UserDto(
    id = id,
    firstName = first_name,
    lastName = last_name,
    username = username,
    email = email,
    phoneNumber = phone_number,
    birthDate = birth_date,
    address1 = address1,
    address2 = address2,
    createdAt = created_at
)