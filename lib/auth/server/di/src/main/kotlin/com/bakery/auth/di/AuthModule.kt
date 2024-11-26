package com.bakery.auth.di

import com.bakery.auth.data.handler.AuthHandler
import com.bakery.auth.data.handler.DefaultAuthHandler
import com.bakery.auth.data.repository.AuthRepository
import com.bakery.auth.data.repository.DefaultAuthRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun authModule(): Module = module {
    singleOf(::DefaultAuthRepository) bind AuthRepository::class

    singleOf(::DefaultAuthHandler) bind AuthHandler::class
}
