package com.material.management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.material.management.database.MongoDbMaterialRepository;
import com.material.management.domain.MaterialRepository;
import com.material.management.domain.entities.Material;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class MaterialApiIT {
    @Autowired
    private MongoDbMaterialRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_create_material_on_receiving_request() throws Exception {
        val request = post("/api/v1/materials")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"name\": \"Sand\"\n" +
                        "}");
        mockMvc.perform(request).andExpect(status().isCreated());
        Material material = repository.findById(2L).get();
        assertAll(
                () -> assertEquals(2, material.getId()),
                () -> assertEquals("Sand", material.getName())
        );
    }

    @Test
    public void should_get_material_when_data_is_available() throws Exception {
        repository.save(new Material(1L, "Cement"));
        val request = get("/api/v1/materials/1")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Cement")));
    }
}
