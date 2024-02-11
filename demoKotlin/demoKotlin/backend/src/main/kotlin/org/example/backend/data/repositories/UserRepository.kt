package org.example.backend.data.repositories

import org.example.backend.data.modelo.UserEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : ListCrudRepository<UserEntity, Long>{

    @EntityGraph(attributePaths = ["roles"])
    fun findByName(name: String): UserEntity?

    @EntityGraph(attributePaths = ["roles"])
    @Query("select u from UserEntity u")
    fun findAllWithRoles(): List<UserEntity>


}
