package org.example.frontend.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import org.example.frontend.common.ServiceResult
import org.example.frontend.domain.modelo.Pelicula
import org.example.peliculas.AddPeliculaMutation
import org.example.peliculas.GetPeliculasQuery
import org.example.peliculas.type.PeliculaInput
import org.springframework.stereotype.Repository

@Repository
class PeliculasRepository(
    var apolloClient: ApolloClient
) {

   suspend fun getPeliculas() =
       apolloClient.query(GetPeliculasQuery()).execute().data?.getPeliculas?.map{
           Pelicula(it.id, it.titulo)
       } ?: emptyList()

    suspend fun addPelicula(pelicula: Pelicula) : ServiceResult<Pelicula> {
        val response = try {
            apolloClient.mutation(
                AddPeliculaMutation(
                    PeliculaInput(
                        id = pelicula.id.toString(),
                        titulo = pelicula.titulo
                    )
                )
            ).execute()
        } catch (exception: ApolloException) {
            // Network error, not much to do
            return ServiceResult.ErrorResult(exception.message ?: "Unknown error")
        }


        return response.data?.let {
            it.addPelicula?.let {
                ServiceResult.Success(Pelicula(it.id, it.titulo))
            }
        } ?: response.errors.let {
            ServiceResult.ErrorResult(it?.let{it.joinToString { it.message }} ?: "Unknown error")
        }

    }
}
