package com.josiah.squirrels.stash.controller;

import com.josiah.squirrels.stash.dto.StashCreateDto;
import com.josiah.squirrels.stash.dto.StashResponseDto;
import com.josiah.squirrels.stash.service.StashService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class StashController {

    private final StashService stashService;
    private static final Logger logger = LoggerFactory.getLogger(StashController.class);

    @Autowired
    public StashController(StashService stashService) {
        this.stashService = stashService;
    }

    @GetMapping("/squirrels/{id}/stashes")
    public ResponseEntity<Page<StashResponseDto>> getStashes(@PathVariable Long id, Pageable pageable) {

        Page<StashResponseDto> stashDto = stashService.getStashes(id, pageable);

        return ResponseEntity.ok(stashDto);
    }

    @PostMapping("/squirrels/{id}/stashes")
    public ResponseEntity<StashResponseDto> createStash(
            @PathVariable Long id,
            @Valid @RequestBody StashCreateDto stashCreateDto ) {

        StashResponseDto stashResponseDto = stashService.createStash(id, stashCreateDto);

        return ResponseEntity
                .created(URI.create("/squirrels/" + id + "/stashes" + stashResponseDto.getId()))
                .body(stashResponseDto);
    }

}
