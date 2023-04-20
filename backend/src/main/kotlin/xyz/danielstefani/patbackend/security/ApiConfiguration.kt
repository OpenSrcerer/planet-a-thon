package xyz.danielstefani.patbackend.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/**
 * This is a configuration class which retrieves the api.key from application.properties.
 */
@Configuration
class ApiConfiguration {
    @Value("\${api.key}")
    var apiKey: String? = null
}