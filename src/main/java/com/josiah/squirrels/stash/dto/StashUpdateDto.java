package com.josiah.squirrels.stash.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StashUpdateDto {

    @NotBlank(message="Location is required")
    @Size(min=5, max=30, message="Location must be between 5 and 30 characters")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
