package xyz.danielstefani.patbackend.exception

import org.springframework.http.HttpStatus

open class GenericHttpException(
    val statusCode: HttpStatus,
    override val message: String
) : RuntimeException()