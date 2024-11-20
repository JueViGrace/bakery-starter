package com.bakery.core.database.driver

import app.cash.sqldelight.db.SqlDriver

interface DriverFactory {
    suspend fun createDriver(): SqlDriver
}

expect class DriverFactoryImpl : DriverFactory
