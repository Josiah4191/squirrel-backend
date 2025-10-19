package com.josiah.squirrels.stash.dto;

import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;
import com.josiah.squirrels.squirrel.entity.Squirrel;

public class StashResponseDto {

    private Long id;
    private String location;
    private SquirrelResponseDto squirrelResponseDto;

    public StashResponseDto(Long id, String location, SquirrelResponseDto squirrelResponseDto) {
        this.id = id;
        this.location = location;
        this.squirrelResponseDto = squirrelResponseDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SquirrelResponseDto getSquirrelResponseDto() {
        return squirrelResponseDto;
    }

    public void setSquirrelResponseDto(SquirrelResponseDto squirrelResponseDto) {
        this.squirrelResponseDto = squirrelResponseDto;
    }
}
