package com.material.management.restapi;

import com.material.management.domain.MaterialCommand;
import com.material.management.domain.MaterialService;
import com.material.management.domain.entities.Material;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class MaterialApiTest {

    private MaterialService materialService;

    private MaterialApi materialApi;

    @BeforeEach
    public void setup() {
        materialService = mock(MaterialService.class);
        materialApi = new MaterialApi(materialService);
    }

    @Test
    public void should_get_status_created_and_call_service_to_create_material() {
        when(materialService.createMaterial(any(MaterialCommand.class))).thenReturn(1L);
        val created = materialApi.create(new MaterialRequest(1L, "Cement"));
        assertAll(
                () -> assertEquals(CREATED, created.getStatusCode()),
                () -> assertEquals(1L, created.getBody()),
                () -> verify(materialService).createMaterial(any(MaterialCommand.class))
                );
    }

    @Test
    public void should_get_status_ok_and_call_service_to_get_material_details() {
        when(materialService.getMaterial(1L)).thenReturn(new Material(1L, "Cement"));
        val material = materialApi.getMaterial(1L);
        assertAll(
                () -> assertEquals(OK, material.getStatusCode()),
                () -> assertEquals(1L, ((Material) material.getBody()).getId()),
                () -> assertEquals("Cement", ((Material) material.getBody()).getName()),
                () -> verify(materialService).getMaterial(anyLong())
        );
    }

}