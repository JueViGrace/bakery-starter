package com.bakery.core.types.client.common

import kotlinx.coroutines.flow.SharingStarted
import kotlin.time.Duration

const val APP_VERSION: String = "1.0.0"

const val UNKNOWN_ERROR = "Unknown error"

const val SERVER_ERROR = "Internal server error"

const val DB_ERROR_MESSAGE = "Database is not available"

const val UNEXPECTED_ERROR = "Unexpected error"

private val STOP_TIME_MILLIS = Duration.parse("5s").inWholeSeconds

val SHARING_STARTED = SharingStarted.WhileSubscribed(STOP_TIME_MILLIS)
