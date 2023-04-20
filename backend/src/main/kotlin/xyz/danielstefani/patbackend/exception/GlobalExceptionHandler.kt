package xyz.danielstefani.patbackend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.*


/**
 * A class dedicated to global exception handling.
 *
 * Exceptions are situation in which the server cannot recover, and therefore will
 * return an erroneous response.
 */
@ControllerAdvice
class GlobalExceptionHandler {

    // If any exception is thrown in a controller, these methods will be called
    // depending on the type of exception.


    /**
     * Handler for the generic "Exception" class.
     * @param ex The exception that was thrown.
     * @param webRequest The web request that threw the exception.
     * @return A JSON object which describes the error.
     */
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

    // There is nothing special about the functions below,
    // all they do is treat other types of errors in a similar way.

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