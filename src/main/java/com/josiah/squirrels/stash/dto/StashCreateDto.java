package com.josiah.squirrels.stash.dto;

import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;

public class StashCreateDto {

    private String location;
    private SquirrelResponseDto squirrelResponseDto;

    public StashCreateDto(String location, SquirrelResponseDto squirrelResponseDto) {
        this.location = location;
        this.squirrelResponseDto = squirrelResponseDto;
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
