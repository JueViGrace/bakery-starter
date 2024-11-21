package com.bakery.core.database.server.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bakery.core.database.driver.DriverFactory
import com.bakery.core.database.server.BakerySvDb

actual class DriverFactoryImpl : DriverFactory {
    override suspend fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:bakery_db")
        BakerySvDb.Schema.create(driver)
        return driver
    }
}
