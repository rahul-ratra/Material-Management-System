package com.material.management.restapi;

import com.material.management.domain.MaterialCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialRequest {
    private Long id;
    private String name;

    public MaterialCommand toCommand() {
        return new MaterialCommand(id, name);
    }
}
