package org.example.frontend.data.retrofit

import okhttp3.Credentials.basic
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class AuthorizationInterceptor (
    val ca: CacheAuthorization,
): okhttp3.Interceptor{


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        var request: Request

        request = if (ca.accesToken.isEmpty()) {
            original.newBuilder()
                .header("Authorization", basic(ca.user, ca.pass)).build()
        } else {
            original.newBuilder()
                .header("Authorization", "Bearer " + ca.accesToken).build()
        }

        var response: Response = chain.proceed(request)
        response.header("Authorization")?.let {
            ca.accesToken = it
        }

        if (!response.isSuccessful) {
            //reintentar
            response.close()
            request = original.newBuilder()
                .header("Authorization", basic(ca.user, ca.pass)).build()
            response = chain.proceed(request)
            response.header("Authorization")?.let {
                ca.accesToken = it
            }
        }

        return response
    }
}
