package org.example.frontend.data.apollo

import com.apollographql.apollo3.interceptor.ApolloInterceptor
import okhttp3.Interceptor
import okhttp3.Response
import org.example.frontend.data.retrofit.CacheAuthorization
import org.springframework.stereotype.Component

@Component
class AuthenticationInterceptor(
    val cacheAuthorization: CacheAuthorization,

    ) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .apply {
                cacheAuthorization.accesToken.let { token ->
                    addHeader("Authorization", "Bearer $token")
                }
            }
            .build()
        return chain.proceed(request)
    }
}

