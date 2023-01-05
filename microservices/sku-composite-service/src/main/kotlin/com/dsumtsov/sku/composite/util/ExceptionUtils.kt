package com.dsumtsov.sku.composite.util

import com.dsumtsov.commons.dto.HttpErrorInfo
import com.dsumtsov.commons.exception.BadRequestException
import com.dsumtsov.commons.exception.InvalidInputException
import com.dsumtsov.commons.exception.NotFoundException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import feign.FeignException
import mu.KotlinLogging
import java.io.IOException

private val log = KotlinLogging.logger {}

object ExceptionUtils {

    fun handleFeignException(ex: Throwable): Throwable {
        if (ex !is FeignException) {
            log.error { "Got an unexpected exception: $ex, rethrowing it" }
            return ex
        }

        when (ex.status()) {
            400 -> return BadRequestException(getErrorMessage(ex))
            404 -> return NotFoundException(getErrorMessage(ex))
            422 -> return InvalidInputException(getErrorMessage(ex))
        }

        return ex
    }

    private fun getErrorMessage(ex: FeignException): String? {
        return try {
            if (ex.responseBody().isPresent) {
                ObjectMapper().registerModule(JavaTimeModule()).readValue(
                    String(ex.responseBody().get().array()),
                    HttpErrorInfo::class.java
                ).message
            } else {
                null
            }
        } catch (e: IOException) {
            e.message
        }
    }
}