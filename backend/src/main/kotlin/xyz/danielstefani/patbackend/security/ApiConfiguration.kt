package xyz.danielstefani.patbackend.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfiguration {
    @Value("\${api.key}")
    var apiKey: String? = null
}