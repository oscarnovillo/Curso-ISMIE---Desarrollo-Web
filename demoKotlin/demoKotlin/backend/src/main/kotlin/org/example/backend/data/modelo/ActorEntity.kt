package org.example.backend.data.modelo

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "actores")
class ActorEntity (
    @Id
    var id: UUID,

    var nombre: String,

    )

