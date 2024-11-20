package com.bakery.core.database.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bakery.core.database.BakerySlDb

actual class DriverFactoryImpl : DriverFactory {
    override suspend fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:bakery_db")
        BakerySlDb.Schema.create(driver)
        return driver
    }
}
