package org.example.backend.ui.auth.modelo

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticationResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("refresh_token")
    val refreshToken: String,

)

