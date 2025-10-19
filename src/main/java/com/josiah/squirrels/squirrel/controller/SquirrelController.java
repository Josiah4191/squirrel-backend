package com.josiah.squirrels.squirrel.controller;

import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;
import com.josiah.squirrels.squirrel.entity.Squirrel;
import com.josiah.squirrels.squirrel.service.SquirrelService;
import com.josiah.squirrels.squirrel.dto.SquirrelCreateDto;
import com.josiah.squirrels.squirrel.dto.SquirrelUpdateDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SquirrelController {

    private final SquirrelService squirrelService;
    private final static Logger logger = LoggerFactory.getLogger(SquirrelController.class);

    @Autowired
    public SquirrelController(SquirrelService squirrelService) {
        this.squirrelService = squirrelService;
    }

    @GetMapping("/squirrels")
    public ResponseEntity<Page<SquirrelResponseDto>> getAllSquirrels(Pageable pageable) {
        Page<SquirrelResponseDto> squirrelResponseDtos = squirrelService.getSquirrels(pageable);

        return ResponseEntity.ok(squirrelResponseDtos);
    }

    @GetMapping("/squirrels/{id}")
    public ResponseEntity<SquirrelResponseDto> getSquirrelById(@PathVariable Long id) {
        SquirrelResponseDto squirrelResponseDto = squirrelService.getSquirrelById(id);

        return ResponseEntity.ok(squirrelResponseDto);
    }

    @PostMapping("/squirrels")
    public ResponseEntity<String> createSquirrel(
            @Valid
            @RequestBody SquirrelCreateDto squirrelCreateDto) {
        String name = squirrelCreateDto.getName();
        Optional<Squirrel> savedSquirrel = squirrelService.createSquirrel(name);

        return savedSquirrel
                .map(value -> ResponseEntity.ok(String.format("%s added successfully", value.getName())))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/squirrels/{id}")
    public ResponseEntity<SquirrelResponseDto> updateSquirrelName(
            @PathVariable Long id,
            @Valid @RequestBody SquirrelUpdateDto squirrelUpdateDto) {
        SquirrelResponseDto squirrelResponseDto = squirrelService.updateSquirrelName(id, squirrelUpdateDto.getName());

        return ResponseEntity.ok(squirrelResponseDto);
    }

    @DeleteMapping("/squirrels/{id}")
    public ResponseEntity<String> deleteSquirrelById(@PathVariable Long id) {

        Optional<Squirrel> squirrel = squirrelService.deleteSquirrelById(id);

        return squirrel.map(value -> ResponseEntity.ok(String.format("Squirrel removed with ID: %d", id)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
