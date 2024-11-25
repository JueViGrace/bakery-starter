package com.bakery.core.shared.types

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

actual object Constants {
    actual const val APP_VERSION: String = "0.0.1"
    actual val currentTime: String = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString()
    actual const val MINIMUM_LENGTH: Int = 4
}
