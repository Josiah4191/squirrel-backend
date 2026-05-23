package com.josiah.squirrels.stash.dto;

import org.springframework.data.domain.Page;

public class StashItemsPageDto {
    private Page<ItemsInStashDto> items;
    private StashResponseDto stash;

    public StashItemsPageDto(StashResponseDto stash, Page<ItemsInStashDto> items) {
        this.items = items;
        this.stash = stash;
    }

    public Page<ItemsInStashDto> getItems() {
        return items;
    }

    public void setItems(Page<ItemsInStashDto> items) {
        this.items = items;
    }

    public StashResponseDto getStash() {
        return stash;
    }

    public void setStash(StashResponseDto stash) {
        this.stash = stash;
    }
}
