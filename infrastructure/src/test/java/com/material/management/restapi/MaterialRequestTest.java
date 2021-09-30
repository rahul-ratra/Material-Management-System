package com.material.management.restapi;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialRequestTest {

    @Test
    public void should_get_command_from_request() {
        val request = new MaterialRequest(1L, "Cement");
        val materialCommand = request.toCommand();
        assertAll(
                () -> assertEquals(1, materialCommand.getId()),
                () -> assertEquals("Cement", materialCommand.getName())
        );
    }

}