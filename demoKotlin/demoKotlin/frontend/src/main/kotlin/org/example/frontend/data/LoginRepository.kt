package org.example.frontend.data

import org.example.frontend.data.modelo.AuthenticationRequest
import org.example.frontend.data.modelo.AuthenticationResponse
import org.example.frontend.data.retrofit.ApiPeliculas
import org.springframework.stereotype.Repository


@Repository
class LoginRepository (
    val apiPeliculas: ApiPeliculas,
){

    suspend fun login(authenticationRequest: AuthenticationRequest) : AuthenticationResponse? {
        val response = apiPeliculas.getLoginToken(authenticationRequest)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
