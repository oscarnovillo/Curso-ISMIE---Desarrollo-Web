package org.example.backend.ui.controllers.graphql

import jakarta.annotation.security.RolesAllowed
import org.example.backend.domain.modelo.Pelicula
import org.example.backend.domain.modelo.PeliculaInput
import org.example.backend.domain.servicios.PeliculasServicios
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class PeliculaController(
    val peliculasServicios: PeliculasServicios

) {


    @QueryMapping
    fun getPeliculas() = peliculasServicios.getPeliculas()


    @QueryMapping
    fun getPelicula(@Argument id: String) = peliculasServicios.getPeliculaById(UUID.fromString(id))

    @MutationMapping
    @RolesAllowed("ADMIN")
    fun addPelicula(@Argument peliculaInput: Pelicula) =
        peliculasServicios.addPelicula(
            peliculaInput
        )
}
