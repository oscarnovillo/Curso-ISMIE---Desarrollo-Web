package com.example.demo.data.repositories;

import com.example.demo.data.modelo.PoiEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiRepository extends ListCrudRepository<PoiEntity, Long> {
}
