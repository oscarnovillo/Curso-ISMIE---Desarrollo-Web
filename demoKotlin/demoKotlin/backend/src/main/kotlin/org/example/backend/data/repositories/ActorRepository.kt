package org.example.backend.data.repositories

import org.example.backend.data.modelo.ActorEntity
import org.example.backend.data.modelo.PeliculaEntity
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.ListPagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ActorRepository :
    ListPagingAndSortingRepository<ActorEntity, UUID>,
    ListCrudRepository<ActorEntity, UUID> {
}
