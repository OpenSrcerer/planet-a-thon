package xyz.danielstefani.patbackend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.*


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleUncaughtException(
        ex: Exception,
        webRequest: WebRequest
    ): ResponseEntity<Map<String, String?>> {
        ex.printStackTrace()
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(mapOf(Pair("error", ex.message ?: "Oops, that wasn't supposed to happen.")))
    }

    @ExceptionHandler(GenericHttpException::class)
    fun handleGenericHttpException(
        ex: GenericHttpException,
        webRequest: WebRequest
    ): ResponseEntity<Map<String, String?>> {
        ex.printStackTrace()
        return ResponseEntity
            .status(ex.statusCode)
            .body(mapOf(Pair("error", ex.message)))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleUnreadableMessageException(
        ex: Exception,
        webRequest: WebRequest
    ): ResponseEntity<Map<String, String?>> {
        return handleBadRequest(ex)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(
        ex: Exception,
        webRequest: WebRequest
    ): ResponseEntity<Map<String, String?>> {
        return handleBadRequest(ex)
    }

    private fun handleBadRequest(ex: Exception): ResponseEntity<Map<String, String?>> {
        ex.printStackTrace()
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(mapOf(Pair("error", ex.message)))
    }
}