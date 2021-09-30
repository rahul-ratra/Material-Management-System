package com.material.management.database;

import com.material.management.domain.MaterialRepository;
import com.material.management.domain.entities.Material;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoDbMaterialRepository extends MaterialRepository, MongoRepository<Material, Long> {

    Optional<Material> findById(Long id);

    Material save(Material material);
}
