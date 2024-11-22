package com.bakery.core.types.shared

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

actual object Constants {
    actual val currentTime: String = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString()
}
