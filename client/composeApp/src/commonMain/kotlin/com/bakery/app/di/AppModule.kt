package com.bakery.app.di

import org.koin.core.module.Module

fun appModule(): List<Module> = listOf(
    databaseModule(),
    ktorModule()
)
