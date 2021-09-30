package com.material.management.domain;
import com.material.management.domain.entities.Material;

import java.util.Optional;

public interface MaterialRepository {
    Optional<Material> findById(Long id);

    Material save(Material material);
}
