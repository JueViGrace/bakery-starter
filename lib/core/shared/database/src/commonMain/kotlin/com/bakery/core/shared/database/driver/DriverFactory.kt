package com.bakery.core.shared.database.driver

import app.cash.sqldelight.db.SqlDriver

interface DriverFactory {
    suspend fun createDriver(): SqlDriver
}
