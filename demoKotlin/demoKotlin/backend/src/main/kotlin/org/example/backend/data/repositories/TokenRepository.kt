package org.example.backend.data.repositories

import org.example.backend.data.modelo.TokenEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TokenRepository : ListCrudRepository<TokenEntity, Long> {

    @Query("""
      select t from TokenEntity t inner join UserEntity u
      on t.user.id = u.id
      where u.name = :name and (t.expired = false or t.revoked = false)
      
      """)
    fun findAllValidTokenByUser(name: String?): List<TokenEntity>
    fun findByToken(jwt: String): TokenEntity?

}
