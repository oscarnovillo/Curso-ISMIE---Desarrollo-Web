package org.example.backend.ui.auth

import jakarta.servlet.http.HttpServletRequest
import org.example.backend.ui.auth.modelo.AuthenticationRequest
import org.example.backend.ui.auth.modelo.AuthenticationResponse
import org.example.backend.ui.auth.servicios.AuthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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



}
