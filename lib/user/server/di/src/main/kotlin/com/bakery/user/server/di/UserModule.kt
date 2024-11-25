package com.bakery.user.server.di

import com.bakery.user.server.data.handler.DefaultUserHandler
import com.bakery.user.server.data.handler.UserHandler
import com.bakery.user.server.data.repository.DefaultUserRepository
import com.bakery.user.server.data.repository.UserRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun userModule(): Module = module {
    singleOf(::DefaultUserRepository) bind UserRepository::class

    singleOf(::DefaultUserHandler) bind UserHandler::class
}
