package com.bakery.app.splash.data

import com.bakery.core.api.KtorClient
import com.bakery.core.database.helper.DbHelper
import com.bakery.core.types.Repository
import com.bakery.core.types.Session
import com.bakery.core.types.state.RequestState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlin.coroutines.CoroutineContext

interface SplashRepository : Repository {
    suspend fun refresh()
    suspend fun validateSession(): Flow<RequestState<Session>>
}

class DefaultSplashRepository(
    override val ktorClient: KtorClient,
    override val dbHelper: DbHelper,
    override val coroutineContext: CoroutineContext,
    override val scope: CoroutineScope
) : SplashRepository {
    override suspend fun refresh() {

    }

    override suspend fun validateSession(): Flow<RequestState<Session>> {
        return emptyFlow()
    }
}
