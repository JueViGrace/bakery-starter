package com.bakery.server.di

import org.koin.core.module.Module

fun serverModule(): List<Module> {
    return listOf(
        databaseModule(),
    )
}
