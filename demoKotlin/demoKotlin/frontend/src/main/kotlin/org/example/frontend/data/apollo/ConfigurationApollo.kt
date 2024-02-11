package org.example.frontend.data.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import org.example.peliculas.type.UUID

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration



@Configuration
class ConfigurationApollo {

    @Bean
    fun createApolloClient(authenticationInterceptor: AuthenticationInterceptor): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://localhost:8080/graphql")
            .addCustomScalarAdapter(UUID.type,uuidAdapter)
            .okHttpClient(
                OkHttpClient.Builder()
                    .addInterceptor(authenticationInterceptor)
                    .build()
            )
            .build()
    }
}
