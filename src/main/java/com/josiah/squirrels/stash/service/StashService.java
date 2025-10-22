package com.josiah.squirrels.stash.service;

import com.josiah.squirrels.common.exception.NotFoundException;
import com.josiah.squirrels.item.repository.ItemRepository;
import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;
import com.josiah.squirrels.squirrel.entity.Squirrel;
import com.josiah.squirrels.squirrel.repository.SquirrelRepository;
import com.josiah.squirrels.stash.dto.*;
import com.josiah.squirrels.stash.entity.Stash;
import com.josiah.squirrels.stash.repository.StashRepository;
import com.josiah.squirrels.stashline.dto.StashLineAddDto;
import com.josiah.squirrels.stashline.dto.StashLineResponseDto;
import com.josiah.squirrels.stashline.service.StashLineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StashService {
    private final StashRepository stashRepo;
    private final SquirrelRepository squirrelRepo;
    private final ItemRepository itemRepo;
    private final StashLineService stashLineService;

    public StashService(StashRepository stashRepo, SquirrelRepository squirrelRepo, ItemRepository itemRepo, StashLineService stashLineService) {
        this.stashRepo = stashRepo;
        this.squirrelRepo = squirrelRepo;
        this.itemRepo = itemRepo;
        this.stashLineService = stashLineService;
    }

    // Get all stashes from a squirrel by squirrel id
    public Page<StashResponseDto> getStashes(Long squirrelId, Pageable pageable) {
        Page<Stash> stash = stashRepo.findBySquirrelId(squirrelId, pageable);
        return stash
                .map(s -> new StashResponseDto(
                        s.getId(),
                        s.getLocation(),
                        new SquirrelResponseDto(
                                s.getSquirrel().getId(),
                                s.getSquirrel().getName())));
    }

    // Get a single stash by id
    public StashResponseDto getStashById(Long stashId) {
        Stash stash = stashRepo.findById(stashId).orElseThrow(() -> new NotFoundException("Stash not found"));

        return new StashResponseDto(
                stash.getId(),
                stash.getLocation(),
                new SquirrelResponseDto(
                        stash.getSquirrel().getId(),
                        stash.getSquirrel().getName()));
    }

    // Create a new stash for a squirrel by squirrel id
    public StashResponseDto createStash(Long squirrelId, StashCreateDto createDto) {
        Squirrel squirrel = squirrelRepo
                .findById(squirrelId)
                .orElseThrow(() -> new NotFoundException("Squirrel not found"));

        Stash stash = new Stash();
        stash.setLocation(createDto.getLocation()); // set location
        stash.setSquirrel(squirrel); // set squirrel
        Stash savedStash = stashRepo.save(stash); // save stash

        return new StashResponseDto(
                savedStash.getId(),
                savedStash.getLocation(),
                new SquirrelResponseDto(
                        savedStash.getSquirrel().getId(),
                        savedStash.getSquirrel().getName()));
    }

    // Update a stash's name by id
    public StashResponseDto updateStashLocation(Long stashId, StashUpdateDto updateDto) {
        Stash stash = stashRepo.findById(stashId)
                .orElseThrow(() -> new NotFoundException("Stash not found"));

        stash.setLocation(updateDto.getLocation());
        Stash savedStash = stashRepo.save(stash);

        return new StashResponseDto(
                savedStash.getId(),
                savedStash.getLocation(),
                new SquirrelResponseDto(
                        savedStash.getSquirrel().getId(),
                        savedStash.getSquirrel().getName()));
    }

    // Delete a stash by id
    public void deleteStash(Long stashId) {
        Stash stash = stashRepo.findById(stashId)
                .orElseThrow(() -> new NotFoundException("Stash not found"));

        stashRepo.delete(stash);
    }

    // Get all items in a stash
    public Page<ItemsInStashDto> getAllItems(Long stashId, Pageable pageable) {
        return stashLineService.getStashLinesByStashId(stashId, pageable);
    }

    // Add an item to a stash
    public StashLineResponseDto addItemToStash(Long stashId, StashAddItemDto addDto) {
        return stashLineService.addStashLine(
                new StashLineAddDto(
                        addDto.getItemId(),
                        stashId,
                        addDto.getQuantity()));
    }

    // Update an item quantity

    // Remove an item


}
