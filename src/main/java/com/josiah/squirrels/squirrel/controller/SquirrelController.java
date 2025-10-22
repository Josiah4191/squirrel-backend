package com.josiah.squirrels.squirrel.controller;

import com.josiah.squirrels.item.dto.ItemResponseDto;
import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;
import com.josiah.squirrels.squirrel.service.SquirrelService;
import com.josiah.squirrels.squirrel.dto.SquirrelCreateDto;
import com.josiah.squirrels.squirrel.dto.SquirrelUpdateDto;
import com.josiah.squirrels.stash.dto.ItemsInStashDto;
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
public class SquirrelController {

    private final SquirrelService service;
    private final static Logger logger = LoggerFactory.getLogger(SquirrelController.class);

    public SquirrelController(SquirrelService service) {
        this.service = service;
    }

    // Get all squirrels
    @GetMapping("/squirrels")
    public ResponseEntity<Page<SquirrelResponseDto>> getAllSquirrels(@ParameterObject Pageable pageable) {
        Page<SquirrelResponseDto> responseDtos = service.getSquirrels(pageable);
        return ResponseEntity.ok(responseDtos);
    }

    // Get a single squirrel by id
    @GetMapping("/squirrels/{squirrel_id}")
    public ResponseEntity<SquirrelResponseDto> getSquirrelById(@PathVariable("squirrel_id") Long squirrelId) {
        SquirrelResponseDto responseDto = service.getSquirrelById(squirrelId);
        return ResponseEntity.ok(responseDto);
    }

    // Get all items for a squirrel by id
    @GetMapping("/squirrels/{squirrel_id}/items")
    public ResponseEntity<Page<ItemsInStashDto>> getAllItems(@PathVariable("squirrel_id") Long squirrelId, @ParameterObject Pageable pageable) {
        Page<ItemsInStashDto> responseDto = service.getAllItems(squirrelId, pageable);
        return ResponseEntity.ok(responseDto);
    }

    // Create a new squirrel
    @PostMapping("/squirrels")
    public ResponseEntity<SquirrelResponseDto> createSquirrel(
            @Valid @RequestBody SquirrelCreateDto createDto) {
        SquirrelResponseDto responseDto = service.createSquirrel(createDto);
        return ResponseEntity
                .created(URI.create("/squirrels/" + responseDto.getId()))
                .body(responseDto);
    }

    // Update a squirrel's name by id
    @PutMapping("/squirrels/{squirrel_id}")
    public ResponseEntity<SquirrelResponseDto> updateSquirrelName(
            @PathVariable("squirrel_id") Long squirrelId, @Valid @RequestBody SquirrelUpdateDto updateDto) {
        SquirrelResponseDto responseDto = service.updateSquirrelName(squirrelId, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    // Delete a squirrel by id
    @DeleteMapping("/squirrels/{squirrel_id}")
    public ResponseEntity<Void> deleteSquirrelById(@PathVariable("squirrel_id") Long squirrelId) {
        service.deleteSquirrel(squirrelId);
        return ResponseEntity.noContent().build();
    }


}
