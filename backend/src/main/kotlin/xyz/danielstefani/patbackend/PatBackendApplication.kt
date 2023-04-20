package xyz.danielstefani.patbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

// This class is the main entry point for the application.
@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
@EnableJpaRepositories
class PatBackendApplication

// This function launches the application.
fun main(args: Array<String>) {
	runApplication<PatBackendApplication>(*args)
}