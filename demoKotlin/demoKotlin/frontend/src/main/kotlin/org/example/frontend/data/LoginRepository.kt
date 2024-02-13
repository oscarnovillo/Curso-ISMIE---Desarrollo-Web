package org.example.frontend.data

import org.example.frontend.common.ServiceResult
import org.example.frontend.data.modelo.AuthenticationRequest
import org.example.frontend.data.modelo.AuthenticationResponse
import org.example.frontend.data.retrofit.ApiPeliculas
import org.springframework.stereotype.Repository


@Repository
class LoginRepository (
    val apiPeliculas: ApiPeliculas,
){

    suspend fun login(authenticationRequest: AuthenticationRequest) : ServiceResult<AuthenticationResponse> =
        runCatching { apiPeliculas.getLoginToken(authenticationRequest) }

            .map { response -> if (response.isSuccessful)
                response.body()?.let { ServiceResult.Success(it) }
                    ?:
                ServiceResult.ErrorResult("parsing error" )
            else ServiceResult.ErrorResult(response.errorBody().toString())
            }
            .getOrDefault(ServiceResult.ErrorResult("Unknown error"))

}
