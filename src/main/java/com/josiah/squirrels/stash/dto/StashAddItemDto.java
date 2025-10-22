package com.josiah.squirrels.stash.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StashAddItemDto {

    @NotNull
    private Long itemId;

    @NotNull
    @Min(value=0, message="Quantity must be greater than or equal to 0")
    private int Quantity;

    public StashAddItemDto(Long itemId, int quantity) {
        this.itemId = itemId;
        Quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
