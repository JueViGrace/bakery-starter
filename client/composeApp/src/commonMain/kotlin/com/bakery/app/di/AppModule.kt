package com.bakery.app.di

import com.bakery.app.data.AppRepository
import com.bakery.app.data.DefaultAppRepository
import com.bakery.app.presentation.viewmodel.AppViewModel
import com.bakery.auth.di.authModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun appModule(): Module = module {
    singleOf(::DefaultAppRepository) bind AppRepository::class

    viewModelOf(::AppViewModel)

    includes(
        authModule(),
    )
}
