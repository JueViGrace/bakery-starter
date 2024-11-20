package com.bakery.app.di

import com.bakery.core.database.driver.DriverFactory
import com.bakery.core.database.driver.DriverFactoryImpl
import com.bakery.core.database.helper.DbHelper
import com.bakery.core.database.helper.DbHelperImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual fun databaseModule(): Module = module {
    singleOf(::DriverFactoryImpl) bind DriverFactory::class

    singleOf(::DbHelperImpl) bind DbHelper::class
}
