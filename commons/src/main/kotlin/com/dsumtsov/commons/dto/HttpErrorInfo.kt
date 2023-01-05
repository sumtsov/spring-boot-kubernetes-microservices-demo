package com.dsumtsov.commons.dto

import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

data class HttpErrorInfo(
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val path: String = "",
    val message: String? = ""
)
