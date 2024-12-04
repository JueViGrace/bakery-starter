package com.bakery.app.di

import com.bakery.app.presentation.viewmodel.AppViewModel
import com.bakery.app.splash.di.splashModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun appModule(): Module = module {
    viewModelOf(::AppViewModel)

    includes(
        splashModule()
    )
}
