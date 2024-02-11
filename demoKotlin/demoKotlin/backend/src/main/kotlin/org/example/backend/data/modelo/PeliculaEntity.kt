package org.example.backend.data.modelo

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "peliculas")
class PeliculaEntity (
    @Id
    var id: UUID,

    var titulo: String,
    ) {
}
