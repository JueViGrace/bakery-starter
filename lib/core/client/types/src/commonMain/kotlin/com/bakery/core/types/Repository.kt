package com.bakery.core.types

import com.bakery.core.api.KtorClient
import com.bakery.core.database.helper.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

interface Repository {
    val ktorClient: KtorClient
    val dbHelper: DbHelper
    val coroutineContext: CoroutineContext
    val scope: CoroutineScope
}
