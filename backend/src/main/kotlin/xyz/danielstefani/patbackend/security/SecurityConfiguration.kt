package xyz.danielstefani.patbackend.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration(
    private val apiConfiguration: ApiConfiguration
) {
    @Bean
    fun authenticationFilter(): PlanetAThonAuthenticationFilter {
        return PlanetAThonAuthenticationFilter(apiConfiguration)
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity.also {
            it.authorizeHttpRequests().anyRequest().hasAuthority("ALL")
            it.addFilterBefore(authenticationFilter(), AuthorizationFilter::class.java)

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