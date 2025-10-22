package com.josiah.squirrels.stashline.dto;

public class StashLineAddDto {

    private Long itemId;
    private Long stashId;
    private int quantity;

    public StashLineAddDto(Long itemId, Long stashId, int quantity) {
        this.itemId = itemId;
        this.stashId = stashId;
        this.quantity = quantity;
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
