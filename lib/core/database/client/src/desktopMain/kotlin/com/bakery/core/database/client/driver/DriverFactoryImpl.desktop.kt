package com.bakery.core.database.client.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bakery.core.database.client.BakeryCliDb
import com.bakery.core.database.shared.driver.DriverFactory

actual class DriverFactoryImpl : DriverFactory {
    override suspend fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:bakery_db")
        BakeryCliDb.Schema.create(driver)
        return driver
    }
}
