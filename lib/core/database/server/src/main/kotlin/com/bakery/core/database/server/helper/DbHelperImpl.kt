package com.bakery.core.database.server.helper

import com.bakery.core.database.server.BakerySvDb
import com.bakery.core.database.shared.driver.DriverFactory
import com.bakery.core.database.shared.helper.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.withLock

class DbHelperImpl(
    override val driver: DriverFactory,
    override val scope: CoroutineScope
) : DbHelper {
    private var db: BakerySvDb? = null

    suspend fun <Result> withDatabase(block: suspend (BakerySvDb) -> Result): Result = mutex.withLock {
        if (db == null) {
            db = createDb()
        }

        return@withLock block(db!!)
    }

    private suspend fun createDb(): BakerySvDb {
        return BakerySvDb(driver.createDriver())
    }
}
