package org.example.frontend.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
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

    suspend fun addPelicula(pelicula: Pelicula) : Pelicula? {
        val response = try {
            apolloClient.mutation(
                AddPeliculaMutation(
                    PeliculaInput(
                        id = pelicula.id.toString(),
                        titulo = pelicula.titulo
                    )
                )
            ).execute()
        } catch(exception: ApolloException) {
            // Network error, not much to do
            throw exception
        }
        return response.data?.let {
            it.addPelicula?.let {
               Pelicula(it.id, it.titulo)
            }
        } ?: response.errors?.let {
            throw Exception(it.joinToString { it.message })
        }

    }
}
