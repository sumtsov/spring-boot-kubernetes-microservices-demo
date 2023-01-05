package com.dsumtsov.commons.config

import com.dsumtsov.commons.dto.HttpErrorInfo
import com.dsumtsov.commons.exception.BadRequestException
import com.dsumtsov.commons.exception.InvalidInputException
import com.dsumtsov.commons.exception.KafkaException
import com.dsumtsov.commons.exception.NotFoundException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    @ResponseBody
    fun handleBadRequestExceptions(
        request: ServerHttpRequest,
        e: BadRequestException
    ): HttpErrorInfo {
        return createHttpErrorInfo(request, HttpStatus.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    @ResponseBody
    fun handleNotFoundException(
        request: ServerHttpRequest,
        e: NotFoundException
    ): HttpErrorInfo {
        return createHttpErrorInfo(request, HttpStatus.NOT_FOUND, e)
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException::class)
    @ResponseBody
    fun handleInvalidInputException(
        request: ServerHttpRequest,
        e: InvalidInputException
    ): HttpErrorInfo {
        return createHttpErrorInfo(request, HttpStatus.UNPROCESSABLE_ENTITY, e)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(KafkaException::class)
    @ResponseBody
    fun handleKafkaException(
        request: ServerHttpRequest,
        e: KafkaException
    ): HttpErrorInfo {
        return createHttpErrorInfo(request, HttpStatus.INTERNAL_SERVER_ERROR, e)
    }

    private fun createHttpErrorInfo(
        request: ServerHttpRequest,
        httpStatus: HttpStatus,
        e: Exception
    ): HttpErrorInfo {
        val path = request.path.pathWithinApplication().value()
        val message = e.message

        log.debug { "Returning HTTP status: $httpStatus for path: $path, message: $message" }

        return HttpErrorInfo(
            httpStatus = httpStatus,
            path = path,
            message = message
        )
    }
}