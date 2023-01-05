package com.dsumtsov.commons.exception

import java.lang.RuntimeException

class KafkaException : RuntimeException {

    constructor(message: String?): super(message)

    constructor(cause: Throwable): super(cause)

    constructor(message: String?, cause: Throwable): super(message, cause)
}