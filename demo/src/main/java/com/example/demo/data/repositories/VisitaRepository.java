package com.example.demo.data.repositories;

import com.example.demo.data.modelo.VisitaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaRepository extends ListCrudRepository<VisitaEntity, Long>{
}
