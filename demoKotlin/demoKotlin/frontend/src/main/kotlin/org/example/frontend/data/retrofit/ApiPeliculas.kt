package org.example.frontend.data.retrofit

import org.example.frontend.data.modelo.AuthenticationRequest
import org.example.frontend.data.modelo.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiPeliculas {

    @POST("/api/auth/loginToken")
    suspend fun getLoginToken(@Body authenticationRequest: AuthenticationRequest): Response<AuthenticationResponse>

}
