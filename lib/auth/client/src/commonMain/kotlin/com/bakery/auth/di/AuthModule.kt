package com.bakery.auth.di

import com.bakery.auth.data.repository.AuthRepository
import com.bakery.auth.data.repository.DefaultAuthRepository
import com.bakery.auth.presentation.viewmodel.AuthViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun authModule(): Module = module {
    singleOf(::DefaultAuthRepository) bind AuthRepository::class

    viewModelOf(::AuthViewModel)
}
