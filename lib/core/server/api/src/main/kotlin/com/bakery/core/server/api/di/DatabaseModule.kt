package com.bakery.core.server.api.di

import com.bakery.core.server.database.driver.DriverFactoryImpl
import com.bakery.core.server.database.helper.DbHelperImpl
import com.bakery.core.shared.database.driver.DriverFactory
import com.bakery.core.shared.database.helper.DbHelper
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun databaseModule(): Module = module {
    singleOf(::DriverFactoryImpl) bind DriverFactory::class

    singleOf(::DbHelperImpl) bind DbHelper::class
}
