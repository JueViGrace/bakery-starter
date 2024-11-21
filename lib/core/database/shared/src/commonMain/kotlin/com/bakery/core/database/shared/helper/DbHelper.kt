package com.bakery.core.database.shared.helper

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.bakery.core.database.shared.driver.DriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex

interface DbHelper {
    val driver: DriverFactory
    val scope: CoroutineScope

    val mutex: Mutex
        get() = Mutex()

    suspend fun <T : Any> executeOne(query: Query<T>): T? {
        return scope.async {
            query.executeAsOneOrNull()
        }.await()
    }

    suspend fun <T : Any> executeOneAsFlow(query: Query<T>): Flow<T?> {
        return scope.async {
            query.asFlow().mapToOneOrNull(coroutineContext)
        }.await()
    }

    suspend fun <T : Any> executeList(query: Query<T>): List<T> {
        return scope.async {
            query.executeAsList()
        }.await()
    }

    suspend fun <T : Any> executeListAsFlow(query: Query<T>): Flow<List<T>> {
        return scope.async {
            query.asFlow().mapToList(coroutineContext)
        }.await()
    }
}
