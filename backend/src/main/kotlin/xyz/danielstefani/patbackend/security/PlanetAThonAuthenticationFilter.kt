package xyz.danielstefani.patbackend.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class PlanetAThonAuthenticationFilter(
    private val apiConfiguration: ApiConfiguration
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.getHeader("PlanetAThonKey")?.equals(apiConfiguration.apiKey) == true) {
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                null, null, mutableListOf(SimpleGrantedAuthority("ALL"))
            )
        }

        filterChain.doFilter(request, response)
    }
}