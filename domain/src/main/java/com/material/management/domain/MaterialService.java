package com.material.management.domain;

import com.material.management.domain.entities.Material;

public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Material getMaterial(Long id) {
        return materialRepository.findById(id).get();
    }

    public Long createMaterial(MaterialCommand command) {
        return materialRepository.save(new Material(command.getId(), command.getName())).getId();
    }
}
