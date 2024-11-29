package com.bakery.auth.data.mappers

import com.bakery.auth.shared.types.SignUpDto
import com.bakery.core.database.Bakery_user
import com.bakery.core.shared.types.Constants
import com.bakery.core.types.Role
import com.bakery.core.util.Kbcrypt
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

typealias BakeryUser = Bakery_user

@OptIn(ExperimentalUuidApi::class)
fun SignUpDto.toDb(): BakeryUser = BakeryUser(
    id = Uuid.random().toString(),
    first_name = firstName,
    last_name = lastName,
    username = username,
    email = email,
    password = Kbcrypt.hashPassword(password),
    phone_number = phoneNumber,
    birth_date = birthDate,
    address1 = address1,
    address2 = address2,
    role = Role.USER.value,
    created_at = Constants.currentTime,
    updated_at = Constants.currentTime,
    deleted_at = null
)
