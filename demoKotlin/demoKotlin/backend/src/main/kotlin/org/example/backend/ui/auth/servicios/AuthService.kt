package org.example.backend.ui.auth.servicios

import org.example.backend.data.modelo.TokenEntity
import org.example.backend.data.modelo.TokenType
import org.example.backend.data.repositories.TokenRepository
import org.example.backend.data.repositories.UserRepository
import org.example.backend.ui.auth.modelo.AuthenticationRequest
import org.example.backend.ui.auth.modelo.AuthenticationResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(

    val repository: UserRepository,
    val tokenRepository: TokenRepository,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager,
    val userDetailsService: UserDetailsService,
) {
    fun authenticate(requestAuth: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                requestAuth.username,
                requestAuth.password,
            )
        )
        val user = userDetailsService.loadUserByUsername(requestAuth.username)
        val jwtToken = jwtService.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)
        revokeAllUserTokens(user.username)
        saveUserToken(user.username, jwtToken)
        return AuthenticationResponse(
            accessToken = jwtToken,
            refreshToken = refreshToken,
        )
    }


    private fun revokeAllUserTokens(username: String) {
        val validUserTokens = tokenRepository.findAllValidTokenByUser(username)
        if (validUserTokens.isEmpty()) return
        validUserTokens.forEach { token ->
            token.expired = true
            token.revoked = true
        }
        tokenRepository.saveAll(validUserTokens)
    }

    private fun saveUserToken(username: String, jwtToken: String) {
        val user = repository.findByName(username)
            ?: throw UsernameNotFoundException("User not found")
        val token = TokenEntity(
            user = user,
            token = jwtToken,
            tokenType = TokenType.BEARER,
            expired = false,
            revoked = false,
        )
        tokenRepository.save(token)
    }
}
