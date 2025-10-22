package com.josiah.squirrels.stashline.dto;

public class StashLineResponseDto {

    private Long id;
    private Long stashId;
    private Long itemId;
    private int quantity;

    public StashLineResponseDto(Long id, Long stashId, Long itemId, int quantity) {
        this.id = id;
        this.itemId = itemId;
        this.stashId = stashId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getStashId() {
        return stashId;
    }

    public void setStashId(Long stashId) {
        this.stashId = stashId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
