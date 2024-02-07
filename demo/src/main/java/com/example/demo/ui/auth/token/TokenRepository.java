package com.example.demo.ui.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

  @Query(value = """
      select t from TokenEntity t inner join UserEntity u
      on t.user.id = u.id
      where u.name = :name and (t.expired = false or t.revoked = false)
      """)
  List<TokenEntity> findAllValidTokenByUser(String name);

  Optional<TokenEntity> findByToken(String token);
}
