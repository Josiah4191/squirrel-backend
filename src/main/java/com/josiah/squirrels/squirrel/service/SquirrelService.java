package com.josiah.squirrels.squirrel.service;

import com.josiah.squirrels.common.exception.NotFoundException;
import com.josiah.squirrels.item.dto.ItemResponseDto;
import com.josiah.squirrels.squirrel.dto.SquirrelCreateDto;
import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;
import com.josiah.squirrels.squirrel.dto.SquirrelUpdateDto;
import com.josiah.squirrels.squirrel.entity.Squirrel;
import com.josiah.squirrels.squirrel.repository.SquirrelRepository;
import com.josiah.squirrels.stash.dto.ItemsInStashDto;
import com.josiah.squirrels.stashline.entity.StashLine;
import com.josiah.squirrels.stashline.service.StashLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SquirrelService {

    private final SquirrelRepository squirrelRepo;
    private static final Logger logger = LoggerFactory.getLogger(SquirrelService.class);
    private final StashLineService stashLineService;

    @Autowired
    public SquirrelService(SquirrelRepository squirrelRepo, StashLineService stashLineService) {
        this.squirrelRepo = squirrelRepo;
        this.stashLineService = stashLineService;
    }

    // Get all squirrels
    public Page<SquirrelResponseDto> getSquirrels(Pageable pageable) {
        Page<Squirrel> squirrels = squirrelRepo.findAll(pageable);

        return squirrels
                .map(s -> new SquirrelResponseDto(s.getId(), s.getName()));
    }

    // Create a new squirrel
    public SquirrelResponseDto createSquirrel(SquirrelCreateDto createDto) {
        Squirrel squirrel = new Squirrel();
        squirrel.setName(createDto.getName());
        Squirrel savedSquirrel = squirrelRepo.save(squirrel);
        return new SquirrelResponseDto(savedSquirrel.getId(), savedSquirrel.getName());
    }

    // Get a single squirrel by id
    public SquirrelResponseDto getSquirrelById(Long squirrelId) {
        Squirrel squirrel = squirrelRepo
                .findById(squirrelId)
                .orElseThrow(() -> new NotFoundException("Squirrel not found"));

        return new SquirrelResponseDto(squirrel.getId(), squirrel.getName());
    }

    // Update a squirrel's name by id
    public SquirrelResponseDto updateSquirrelName(Long squirrelId, SquirrelUpdateDto updateDto) {
        Squirrel squirrel = squirrelRepo
                .findById(squirrelId)
                .orElseThrow(() -> new NotFoundException("Squirrel not found"));

        squirrel.setName(updateDto.getName());
        Squirrel savedSquirrel = squirrelRepo.save(squirrel);

        return new SquirrelResponseDto(savedSquirrel.getId(), savedSquirrel.getName());
    }

    // Delete a squirrel by id
    public void deleteSquirrel(Long squirrelId) {
        Squirrel squirrel = squirrelRepo
                .findById(squirrelId)
                .orElseThrow(() -> new NotFoundException("Squirrel not found."));

        squirrelRepo.delete(squirrel);
    }

    // Get all items for a squirrel
    public Page<ItemsInStashDto> getAllItems(Long squirrelId, Pageable pageable) {
        return stashLineService.getStashLinesBySquirrelId(squirrelId, pageable);
    }
}
