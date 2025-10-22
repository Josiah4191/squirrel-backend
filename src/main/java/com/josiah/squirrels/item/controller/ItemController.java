package com.josiah.squirrels.item.controller;

import com.josiah.squirrels.item.dto.ItemCreateDto;
import com.josiah.squirrels.item.dto.ItemResponseDto;
import com.josiah.squirrels.item.dto.ItemUpdateDto;
import com.josiah.squirrels.item.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ItemController {

    private final ItemService service;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService service) {
        this.service = service;
    }

    // get all items
    @GetMapping("/items")
    public ResponseEntity<Page<ItemResponseDto>> getAllItems(@ParameterObject Pageable pageable) {
        Page<ItemResponseDto> responseDto = service.getAllItems(pageable);
        return ResponseEntity.ok(responseDto);
    }

    //get item by id
    @GetMapping("/items/{item_id}")
    public ResponseEntity<ItemResponseDto> getItem(@PathVariable("item_id") Long itemId) {
        ItemResponseDto responseDto = service.getItem(itemId);
        return ResponseEntity.ok(responseDto);
    }

    // add item by name and description
     @PostMapping("/items")
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemCreateDto createDto) {
        ItemResponseDto responseDto = service.createItem(createDto);
        return ResponseEntity
                .created(URI.create("/items/" + responseDto.getId()))
                .body(responseDto);
    }

    // update item by id
    @PutMapping("/items/{item_id}")
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable("item_id") Long itemId, @RequestBody ItemUpdateDto updateDto) {
        ItemResponseDto responseDto = service.updateItem(itemId, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    // delete item by id
    @DeleteMapping("/items/{item_id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("item_id") Long itemId) {
        service.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }


}
