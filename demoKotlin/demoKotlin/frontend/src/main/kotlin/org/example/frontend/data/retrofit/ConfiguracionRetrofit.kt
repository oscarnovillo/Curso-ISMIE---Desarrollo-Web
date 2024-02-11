package org.example.frontend.data.retrofit

import com.google.gson.*
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.net.CookieManager
import java.net.CookiePolicy
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

@Configuration
class ConfiguracionRetrofit {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun getGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(
                LocalDateTime::class.java,
                JsonDeserializer { json: JsonElement, type: Type?, jsonDeserializationContext: JsonDeserializationContext? ->
                    LocalDateTime.parse(
                        json.asJsonPrimitive.asString
                    )
                } as JsonDeserializer<LocalDateTime>)
            .registerTypeAdapter(
                LocalDateTime::class.java,
                JsonSerializer { localDateTime: LocalDateTime, type: Type?, jsonSerializationContext: JsonSerializationContext? ->
                    JsonPrimitive(
                        localDateTime.toString()
                    )
                } as JsonSerializer<LocalDateTime>)
            .registerTypeAdapter(
                LocalDate::class.java,
                JsonDeserializer { json: JsonElement, type: Type?, jsonDeserializationContext: JsonDeserializationContext? ->
                    LocalDate.parse(
                        json.asJsonPrimitive.asString
                    )
                } as JsonDeserializer<LocalDate>)
            .registerTypeAdapter(
                LocalDate::class.java,
                JsonSerializer { localDate: LocalDate, type: Type?, jsonSerializationContext: JsonSerializationContext? ->
                    JsonPrimitive(
                        localDate.toString()
                    )
                } as JsonSerializer<LocalDate>
            ).create()
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun getOkHttpClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        return OkHttpClient.Builder()
            .readTimeout(Duration.of(10, ChronoUnit.MINUTES))
            .callTimeout(Duration.of(10, ChronoUnit.MINUTES))
            .connectTimeout(Duration.of(10, ChronoUnit.MINUTES))
            .addInterceptor(authorizationInterceptor)
            .connectionPool(ConnectionPool(1, 1, TimeUnit.SECONDS)) // necesario para la sesion
            .build()
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun retrofits(gson: Gson, clientOK: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(clientOK)
            .build()
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun getApiPeliculas(retrofit: Retrofit): ApiPeliculas {
        return retrofit.create(ApiPeliculas::class.java)
    }

}
