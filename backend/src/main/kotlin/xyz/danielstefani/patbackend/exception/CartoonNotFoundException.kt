package xyz.danielstefani.patbackend.exception

import org.springframework.http.HttpStatus


/**
 * Exception we propagate in the case that a cartoon was not found.
 */
class CartoonNotFoundException :
    GenericHttpException(HttpStatus.NOT_FOUND, "The cartoon with the provided ID does not exist.")