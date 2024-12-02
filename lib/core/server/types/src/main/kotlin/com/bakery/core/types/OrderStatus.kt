package com.bakery.core.types

enum class OrderStatus(val value: String) {
    PLACED("placed"),
    CANCELLED("cancelled"),
    DISPATCHED("dispatched"),
    DELIVERED("delivered")
}
