package com.bakery.core.api.di

import com.bakery.auth.di.authModule
import com.bakery.user.di.userModule
import org.koin.core.module.Module

fun serverModule(): List<Module> = listOf(
    coroutinesModule(),
    databaseModule(),
    utilModule(),
    authModule(),
    userModule()
)
