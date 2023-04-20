package xyz.danielstefani.patbackend.exception

import org.springframework.http.HttpStatus

class CartoonNotFoundException : GenericHttpException(HttpStatus.NOT_FOUND, "The cartoon with the provided ID does not exist.")