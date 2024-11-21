package com.bakery.core.database.client.driver

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.bakery.core.database.client.BakeryCliDb
import com.bakery.core.database.shared.driver.DriverFactory

actual class DriverFactoryImpl(
    private val context: Context
) : DriverFactory {
    override suspend fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = BakeryCliDb.Schema.synchronous(),
            context = context,
            name = "bakery.db"
        )
    }
}
