package com.bakery.core.di

import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.Module
import org.koin.dsl.module

fun stateModule(): Module = module {
    single {
        SavedStateHandle()
    }
}
