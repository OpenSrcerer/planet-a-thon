package xyz.danielstefani.patbackend.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

/**
 * This class implements an authentication filter, which is used to "validate user credentials".
 * The user credentials are "validated" when you pass the PlanetAThonKey header with the correct value, which matches
 * your provided api.key in application.properties.
 *
 * Obviously this implementation is trivial and insecure, and just for demonstration purposes.
 */
class PlanetAThonAuthenticationFilter(
    // The filter requires an instance of the API configuration, which contains the API key used to authenticate requests.
    private val apiConfiguration: ApiConfiguration
) : OncePerRequestFilter() {
    // This function executes the filter logic for each incoming request.
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Check if a header with key "PlanetAThonKey" exists and if its value matches the API key.
        if (request.getHeader("PlanetAThonKey")?.equals(apiConfiguration.apiKey) == true) {
            // If the API key matches, set the user authentication context to a new anonymous user with full permissions.
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                null, null, mutableListOf(SimpleGrantedAuthority("ALL"))
            )
        }

        // Continue the filter chain for this request.
        filterChain.doFilter(request, response)
    }
}