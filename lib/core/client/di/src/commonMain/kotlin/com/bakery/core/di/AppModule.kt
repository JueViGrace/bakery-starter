package com.bakery.core.di

import org.koin.core.module.Module

fun appModule(): List<Module> = listOf(
    databaseModule(),
    ktorModule(),
    stateModule(),
)
