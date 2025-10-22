package com.josiah.squirrels.item.service;

import com.josiah.squirrels.common.exception.NotFoundException;
import com.josiah.squirrels.item.dto.ItemCreateDto;
import com.josiah.squirrels.item.dto.ItemResponseDto;
import com.josiah.squirrels.item.dto.ItemUpdateDto;
import com.josiah.squirrels.item.entity.Item;
import com.josiah.squirrels.item.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepo;
    private Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    // get all items - return Page<ItemResponseDto>
    @Cacheable("items")
    public Page<ItemResponseDto> getAllItems(Pageable pageable) {
        return itemRepo.findAll(pageable)
                .map(item -> new ItemResponseDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription()));
    }

    // get item by id - return ItemResponseDto
    @Cacheable("item")
    public ItemResponseDto getItem(Long itemId) {
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        return new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription());
    }

    // add item - return ItemResponseDto
    public ItemResponseDto createItem(ItemCreateDto createDto) {
        Item item = new Item(createDto.getName(), createDto.getDescription());
        itemRepo.save(item);

        return new ItemResponseDto(item.getId(), item.getName(), item.getDescription());
    }

    // update item by id - return ItemResponseDto
    @CachePut(value="item", key="#itemId")
    public ItemResponseDto updateItem(Long itemId, ItemUpdateDto updateDto) {
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
        item.setName(updateDto.getName());
        item.setDescription(updateDto.getDescription());
        Item savedItem = itemRepo.save(item);

        return new ItemResponseDto(savedItem.getId(), savedItem.getName(), savedItem.getDescription());
    }

    // delete item by id - return void
    @Caching(evict = {
            @CacheEvict(value = "items", allEntries = true),
            @CacheEvict(value = "item", key = "#itemId")})
    public void deleteItem(Long itemId) {
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
        itemRepo.delete(item);
    }

}
