package com.bakery.auth.server.di

import com.bakery.auth.server.data.handler.AuthHandler
import com.bakery.auth.server.data.handler.DefaultAuthHandler
import com.bakery.auth.server.data.repository.AuthRepository
import com.bakery.auth.server.data.repository.DefaultAuthRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun authModule(): Module = module {
    singleOf(::DefaultAuthRepository) bind AuthRepository::class

    singleOf(::DefaultAuthHandler) bind AuthHandler::class
}
