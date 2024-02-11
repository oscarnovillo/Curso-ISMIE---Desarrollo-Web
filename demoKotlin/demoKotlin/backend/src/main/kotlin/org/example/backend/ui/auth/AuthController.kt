package org.example.backend.ui.auth

import jakarta.servlet.http.HttpServletRequest
import org.example.backend.ui.auth.modelo.AuthenticationRequest
import org.example.backend.ui.auth.modelo.AuthenticationResponse
import org.example.backend.ui.auth.servicios.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController (
    val authService: AuthService,

) {


    @GetMapping("/loginToken")
    fun loginToken(
        @RequestBody requestAuth: AuthenticationRequest,
        request: HttpServletRequest
    ): AuthenticationResponse {
        return authService.authenticate(requestAuth)
    }

    @PostMapping("/loginToken")
    fun loginTokenConPost(
        @RequestBody requestAuth: AuthenticationRequest,
        request: HttpServletRequest
    ): AuthenticationResponse {
        return authService.authenticate(requestAuth)
    }



}
