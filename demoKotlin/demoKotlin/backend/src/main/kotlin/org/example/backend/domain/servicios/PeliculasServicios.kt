package org.example.backend.domain.servicios

import org.example.backend.data.modelo.toPelicula
import org.example.backend.data.modelo.toPeliculaEntity
import org.example.backend.data.repositories.PeliculaRepository
import org.example.backend.domain.errors.NotFoundException
import org.example.backend.domain.modelo.Pelicula
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class PeliculasServicios (
    val peliculasRepository: PeliculaRepository,

) {

    fun getPeliculas() = peliculasRepository.findAll().map { it.toPelicula() }.toList()

    fun getPeliculaById(id: UUID) =
        peliculasRepository.findByIdOrNull(id)?.toPelicula()
            ?: throw NotFoundException(" Pelicula con $id not found")
    fun addPelicula(pelicula: Pelicula) =
        peliculasRepository.save(pelicula.toPeliculaEntity())



}
