package org.example.backend.ui.auth.servicios

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.backend.data.repositories.TokenRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Service

@Service
class LogoutService(
    var tokenRepository: TokenRepository
) : LogoutHandler {
    override fun logout(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val authHeader : String? = request.getHeader("Authorization")
        authHeader?.let {header->
            if (!header.startsWith("Bearer ")) {
                return
            }
            val jwt = authHeader.substring(7)
            tokenRepository.findByToken(jwt)?.let {token ->
                token.expired = true
                token.revoked = true
                tokenRepository.save(token)
                SecurityContextHolder.clearContext()
            }
        }
    }
}

