package org.example.backend.data.repositories

import org.example.backend.data.modelo.RolEntity
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RolRepository : ListCrudRepository<RolEntity, Long>{
}
