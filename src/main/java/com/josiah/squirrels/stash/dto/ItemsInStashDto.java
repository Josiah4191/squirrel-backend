package com.josiah.squirrels.stash.dto;

import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;

public class ItemsInStashDto {

    private Long id;
    private Long stashLineId;
    private String name;
    private String description;
    private int quantity;

    public ItemsInStashDto(Long id, Long stashLineId, String name, String description, int quantity) {
        this.id = id;
        this.stashLineId = stashLineId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStashLineId() {
        return stashLineId;
    }

    public void setStashLineId(Long stashLineId) {
        this.stashLineId = stashLineId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
