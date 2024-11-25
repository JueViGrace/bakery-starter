package com.bakery.core.server.database.helper

import com.bakery.core.server.database.BakerySvDb
import com.bakery.core.shared.database.driver.DriverFactory
import com.bakery.core.shared.database.helper.DbHelper
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
