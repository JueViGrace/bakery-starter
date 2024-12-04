package com.bakery.app.splash.di

import com.bakery.app.splash.data.DefaultSplashRepository
import com.bakery.app.splash.data.SplashRepository
import com.bakery.app.splash.presentation.viewmodel.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun splashModule(): Module = module {
    singleOf(::DefaultSplashRepository) bind SplashRepository::class

    viewModelOf(::SplashViewModel)
}
