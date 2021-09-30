package com.material.management.domain;

import com.material.management.domain.entities.Material;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.*;

public class MaterialServiceTest {

    private MaterialRepository materialRepository;

    private MaterialService service;

    private ArgumentCaptor<Material> repoArgumentCaptor;

    @BeforeEach
    public void init() {
        materialRepository = mock(MaterialRepository.class);
        service = new MaterialService(materialRepository);
        when(materialRepository.findById(1L)).thenReturn(of(new Material(1L, "Cement")));
        repoArgumentCaptor = forClass(Material.class);
        when(materialRepository.save(repoArgumentCaptor.capture())).thenReturn(new Material(1L, "Cement"));
    }

    @Test
    public void should_call_material_repository_to_get_materila() {
        service.getMaterial(1L);

        verify(materialRepository).findById(1L);
    }

    @Test
    public void should_call_material_repository_to_create_material_and_return_id() {
        Long id = service.createMaterial(new MaterialCommand(1L, "Cement"));

        assertAll(
                () -> assertEquals(1L, id),
                () -> verify(materialRepository).save(any(Material.class))
        );
    }

    @Test
    public void should_call_material_repository_with_values_from_command_to_create_material() {
        service.createMaterial(new MaterialCommand(1L, "Cement"));
        val material = repoArgumentCaptor.getValue();
        assertAll(
                () -> assertEquals(1L, material.getId()),
                () -> assertEquals("Cement", material.getName())
        );
    }


}