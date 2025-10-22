package com.josiah.squirrels.squirrel.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SquirrelUpdateDto {

    @NotBlank(message="Name is required")
    @Size(min=2, max=40, message="Name must be between 2 and 20 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
