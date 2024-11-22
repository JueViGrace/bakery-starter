package com.bakery.server.di

import com.bakery.core.database.server.driver.DriverFactoryImpl
import com.bakery.core.database.server.helper.DbHelperImpl
import com.bakery.core.database.shared.driver.DriverFactory
import com.bakery.core.database.shared.helper.DbHelper
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun databaseModule(): Module = module {
    singleOf(::DriverFactoryImpl) bind DriverFactory::class

    singleOf(::DbHelperImpl) bind DbHelper::class
}
