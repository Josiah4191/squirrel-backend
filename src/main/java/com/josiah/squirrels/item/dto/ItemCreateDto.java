package com.josiah.squirrels.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ItemCreateDto {

    @NotBlank
    @Size(min=2, max=40, message="Name must be between 2 and 40 characters")
    private String name;

    @NotBlank
    @Size(min=10, max=255, message="Description must be between 10 and 255 characters")
    private String description;

    public ItemCreateDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
