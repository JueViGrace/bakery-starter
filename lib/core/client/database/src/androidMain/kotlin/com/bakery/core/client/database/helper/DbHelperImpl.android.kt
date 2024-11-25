package com.bakery.core.client.database.helper

import com.bakery.core.database.client.BakeryCliDb
import com.bakery.core.shared.database.driver.DriverFactory
import com.bakery.core.shared.database.helper.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.withLock

actual class DbHelperImpl(
    override val driver: DriverFactory,
    override val scope: CoroutineScope
) : DbHelper {
    private var db: BakeryCliDb? = null

    suspend fun <Result> withDatabase(block: suspend (BakeryCliDb) -> Result): Result = mutex.withLock {
        if (db == null) {
            db = createDb()
        }

        return@withLock block(db!!)
    }

    private suspend fun createDb(): BakeryCliDb {
        return BakeryCliDb(driver.createDriver())
    }
}
