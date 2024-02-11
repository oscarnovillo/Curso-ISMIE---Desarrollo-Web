package org.example.frontend.data.retrofit

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
data class CacheAuthorization(
    var accesToken: String = "",
    var refreshToken: String = "",
    var user : String  ="",
    var pass : String = "",

    )
