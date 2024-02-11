package org.example.backend.ui.controllers.rest

import org.example.backend.domain.modelo.Pelicula
import org.example.backend.domain.servicios.PeliculasServicios
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/peliculas")
class PeliculaRestController(
    val peliculasServicios: PeliculasServicios,

) {



    @RequestMapping
    fun getPeliculas() = peliculasServicios.getPeliculas()

    @PostMapping
    fun addPelicula(@RequestBody pelicula: Pelicula) = peliculasServicios.addPelicula(pelicula)


}
