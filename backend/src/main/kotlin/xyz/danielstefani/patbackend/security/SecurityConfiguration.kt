package xyz.danielstefani.patbackend.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter

// This class is responsible for configuring the security settings of the application.
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration(
    // The configuration requires a reference to the API configuration,
    // which is used to configure the authentication filter.
    private val apiConfiguration: ApiConfiguration
) {
    // Define a bean for the PlanetAThonAuthenticationFilter.
    @Bean
    fun authenticationFilter(): PlanetAThonAuthenticationFilter {
        return PlanetAThonAuthenticationFilter(apiConfiguration)
    }

    // Define a filter chain that applies the authentication filter and sets permissions for all requests.
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity.also {
            // Set authorization rules for all HTTP requests.
            it.authorizeHttpRequests().anyRequest().hasAuthority("ALL")

            // Add the authentication filter before the authorization filter.
            it.addFilterBefore(authenticationFilter(), AuthorizationFilter::class.java)

            // Disable unnecessary security features.
            it.cors().disable()
            it.csrf().disable()
            it.formLogin().disable()
            it.httpBasic().disable()
            it.headers().cacheControl()
            it.jee().disable()
            it.anonymous().disable()
        }.build()
    }
}