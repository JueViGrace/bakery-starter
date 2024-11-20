package com.bakery.core.database.helper

import com.bakery.core.database.BakerySlDb
import com.bakery.core.database.driver.DriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.withLock

actual class DbHelperImpl(
    override val driver: DriverFactory,
    override val scope: CoroutineScope
) : DbHelper {
    private var db: BakerySlDb? = null

    suspend fun <Result> withDatabase(block: suspend (BakerySlDb) -> Result): Result = mutex.withLock {
        if (db == null) {
            db = createDb()
        }

        return@withLock block(db!!)
    }

    private suspend fun createDb(): BakerySlDb {
        return BakerySlDb(driver.createDriver())
    }
}
