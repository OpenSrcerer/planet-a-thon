package xyz.danielstefani.patbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
@EnableJpaRepositories
class PatBackendApplication

fun main(args: Array<String>) {
	runApplication<PatBackendApplication>(*args)
}