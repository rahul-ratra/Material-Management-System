package com.material.management.restapi;

import com.material.management.domain.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/materials")
public class MaterialApi {

    private final MaterialService materialService;

    public MaterialApi(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping(path = "/{id}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity getMaterial(@PathVariable("id") Long id) {
        return ResponseEntity.ok(materialService.getMaterial(id));
    }

    @PostMapping(consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity create(@RequestBody MaterialRequest request) {
        return ResponseEntity.status(CREATED).body(materialService.createMaterial(request.toCommand()));
    }
}
