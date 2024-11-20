package com.bakery.app.di

import com.bakery.core.api.client.KtorClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun ktorModule(): Module = module {
    singleOf(::KtorClient)
}
