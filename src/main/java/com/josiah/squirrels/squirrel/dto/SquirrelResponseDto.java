package com.josiah.squirrels.squirrel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SquirrelResponseDto {

    @NotNull(message="Id is required")
    private Long id;

    @NotBlank(message="Name is required")
    @Size(min=2, max=40, message="Name must be between 2 and 20 characters")
    private String name;

    public SquirrelResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
