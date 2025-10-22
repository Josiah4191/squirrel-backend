package com.josiah.squirrels.stash.controller;

import com.josiah.squirrels.stash.dto.*;
import com.josiah.squirrels.stash.service.StashService;
import com.josiah.squirrels.stashline.dto.StashLineResponseDto;
import com.josiah.squirrels.stashline.service.StashLineService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class StashController {

    private final StashService service;
    private static final Logger logger = LoggerFactory.getLogger(StashController.class);
    private final StashLineService stashLineService;

    public StashController(StashService service, StashLineService stashLineService) {
        this.service = service;
        this.stashLineService = stashLineService;
    }

    // Get all stashes by squirrel id
    @GetMapping("/squirrels/{squirrel_id}/stashes")
    public ResponseEntity<Page<StashResponseDto>> getStashes(@PathVariable("squirrel_id") Long squirrelId, @ParameterObject Pageable pageable) {
        Page<StashResponseDto> stashDto = service.getStashes(squirrelId, pageable);
        return ResponseEntity.ok(stashDto);
    }

    // Get a single stash by squirrel id
    @GetMapping("/stashes/{stash_id}")
    public ResponseEntity<StashResponseDto> getStashById(@PathVariable("stash_id") Long stashId) {
        StashResponseDto responseDto = service.getStashById(stashId);
        return ResponseEntity.ok(responseDto);
    }

    // Create a stash for a squirrel by squirrel id
    @PostMapping("/squirrels/{squirrel_id}/stashes")
    public ResponseEntity<StashResponseDto> createStash(
            @PathVariable("squirrel_id") Long squirrelId,
            @Valid @RequestBody StashCreateDto createDto ) {
        StashResponseDto responseDto = service.createStash(squirrelId, createDto);
        return ResponseEntity
                .created(URI.create(String.format("/squirrels/%d/stashes/%d", squirrelId, responseDto.getId())))
                .body(responseDto);
    }

    // Update a stash's name by id
    @PutMapping("/stashes/{stash_id}")
    public ResponseEntity<StashResponseDto> updateStashLocation(@PathVariable("stash_id") Long stashId, @Valid @RequestBody StashUpdateDto updateDto) {
        StashResponseDto responseDto = service.updateStashLocation(stashId, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    // Delete a stash by id
    @DeleteMapping("/stashes/{stash_id}")
    public ResponseEntity<Void> deleteStash(@PathVariable("stash_id") Long stashId) {
        service.deleteStash(stashId);
        return ResponseEntity.noContent().build();
    }

    // Get all items in a stash by id
    @GetMapping("/stashes/{stash_id}/items")
    public ResponseEntity<Page<ItemsInStashDto>> getItems(@PathVariable("stash_id") Long stashId, @ParameterObject Pageable pageable) {
        Page<ItemsInStashDto> responseDto = service.getAllItems(stashId, pageable);
        return ResponseEntity.ok(responseDto);
    }

    // Add item to the stash by stash id
    @PostMapping("/stashes/{stash_id}")
    public ResponseEntity<StashLineResponseDto> addItemToStash(@PathVariable("stash_id") Long stashId, @Valid @RequestBody StashAddItemDto addItemDto) {
        StashLineResponseDto responseDto = service.addItemToStash(stashId, addItemDto);
        return ResponseEntity
                .created(URI.create(String.format("/stashes/%d/items/%d", stashId, responseDto.getItemId())))
                .body(responseDto);
    }



}
