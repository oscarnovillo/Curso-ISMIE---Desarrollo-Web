package com.example.demo.data.repositories;

import com.example.demo.data.modelo.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, UUID> {

//    @Query("select u from UserEntity u where u.id = UUID_TO_BIN(:id)")
//    @Override
//    Optional<UserEntity> findById(@Param(value = "id") UUID id);

}