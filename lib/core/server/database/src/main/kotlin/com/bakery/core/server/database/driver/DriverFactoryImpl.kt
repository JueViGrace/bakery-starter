package com.bakery.core.server.database.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bakery.core.server.database.BakerySvDb
import com.bakery.core.shared.database.driver.DriverFactory

class DriverFactoryImpl : DriverFactory {
    override suspend fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:bakery_db")
        BakerySvDb.Schema.create(driver)
        return driver
    }
}
