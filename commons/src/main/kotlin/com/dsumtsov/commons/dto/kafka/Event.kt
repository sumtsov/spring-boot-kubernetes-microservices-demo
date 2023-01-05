package com.dsumtsov.commons.dto.kafka

import java.time.ZonedDateTime

data class Event<T>(
    val type: Type? = null,
    val key: Long? = null,
    val data: T? = null,
    val timestamp: ZonedDateTime = ZonedDateTime.now()
) {
    enum class Type {
        CREATE,
        DELETE
    }
}
