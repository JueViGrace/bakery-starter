package com.bakery.core.server.api.di

import com.bakery.auth.server.di.authModule
import com.bakery.user.server.di.userModule
import org.koin.core.module.Module

fun serverModule(): List<Module> = listOf(
    databaseModule(),
    authModule(),
    userModule()
)
