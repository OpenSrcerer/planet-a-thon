package xyz.danielstefani.patbackend.exception

import org.springframework.http.HttpStatus


/**
 * A generic exception class used to identify all sorts of HTTP errors.
 */
open class GenericHttpException(
    val statusCode: HttpStatus,
    override val message: String
) : RuntimeException()