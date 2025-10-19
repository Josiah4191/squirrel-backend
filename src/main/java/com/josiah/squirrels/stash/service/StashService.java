package com.josiah.squirrels.stash.service;

import com.josiah.squirrels.common.exception.NotFoundException;
import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;
import com.josiah.squirrels.squirrel.entity.Squirrel;
import com.josiah.squirrels.squirrel.repository.SquirrelRepository;
import com.josiah.squirrels.stash.dto.StashCreateDto;
import com.josiah.squirrels.stash.dto.StashResponseDto;
import com.josiah.squirrels.stash.entity.Stash;
import com.josiah.squirrels.stash.repository.StashRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StashService {
    private final SquirrelRepository squirrelRepository;
    private StashRepository stashRepository;

    public StashService(StashRepository stashRepository, SquirrelRepository squirrelRepository) {
        this.stashRepository = stashRepository;
        this.squirrelRepository = squirrelRepository;
    }

    public Page<StashResponseDto> getStashes(Long id, Pageable pageable) {

        Page<Stash> stash = stashRepository.findBySquirrelId(id, pageable);
        Page<StashResponseDto> stashDto = stash
                .map(s -> new StashResponseDto(
                        s.getId(), s.getLocation(),
                        new SquirrelResponseDto(
                                s.getSquirrel().getId(),
                                s.getSquirrel().getName())));
        return stashDto;
    }

    public StashResponseDto createStash(Long id, StashCreateDto stashCreateDto) {

        // Get squirrel by the id
        Squirrel squirrel = squirrelRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Squirrel not found"));

        // Create stash
        Stash stash = new Stash();
        stash.setLocation(stashCreateDto.getLocation()); // set location
        stash.setSquirrel(squirrel); // set squirrel
        Stash savedStash = stashRepository.save(stash); // save stash

        return new StashResponseDto(
                savedStash.getId(),
                savedStash.getLocation(),
                new SquirrelResponseDto(
                        savedStash.getSquirrel().getId(),
                        savedStash.getSquirrel().getName()));
    }
}
