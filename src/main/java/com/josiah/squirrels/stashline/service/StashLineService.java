package com.josiah.squirrels.stashline.service;

import com.josiah.squirrels.common.exception.NotFoundException;
import com.josiah.squirrels.item.dto.ItemResponseDto;
import com.josiah.squirrels.item.entity.Item;
import com.josiah.squirrels.item.repository.ItemRepository;
import com.josiah.squirrels.stash.dto.ItemsInStashDto;
import com.josiah.squirrels.stash.entity.Stash;
import com.josiah.squirrels.stash.repository.StashRepository;
import com.josiah.squirrels.stashline.dto.StashLineAddDto;
import com.josiah.squirrels.stashline.dto.StashLineResponseDto;
import com.josiah.squirrels.stashline.dto.StashLineUpdateDto;
import com.josiah.squirrels.stashline.entity.StashLine;
import com.josiah.squirrels.stashline.repository.StashLineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StashLineService {

    private final StashLineRepository stashLineRepo;
    private final StashRepository stashrepo;
    private final ItemRepository itemRepo;
    private static final Logger logger = LoggerFactory.getLogger(StashLineService.class);

    public StashLineService(ItemRepository itemRepo, StashRepository stashrepo, StashLineRepository stashLineRepo) {
        this.itemRepo = itemRepo;
        this.stashrepo = stashrepo;
        this.stashLineRepo = stashLineRepo;
    }

    // add a stash line - return a StashLineResponseDto
    public StashLineResponseDto addStashLine(StashLineAddDto createDto) {
        // get item
        Item item = itemRepo.findById(createDto.getItemId()).orElseThrow(() -> new NotFoundException("Item not found"));
        // get stash
        Stash stash = stashrepo.findById(createDto.getStashId()).orElseThrow(() -> new NotFoundException("Stash not found"));

        // create and add stash line
        StashLine stashLine = new StashLine();
        stashLine.setItem(item);
        stashLine.setStash(stash);
        stashLine.setQuantity(createDto.getQuantity());
        StashLine savedStashLine = stashLineRepo.save(stashLine);

        // return stash line dto
        return new StashLineResponseDto(
                savedStashLine.getId(),
                savedStashLine.getItem().getId(),
                savedStashLine.getStash().getId(),
                savedStashLine.getQuantity());
    }

    // get all stash lines with a given stash_id
    public Page<ItemsInStashDto> getStashLinesByStashId(Long stashId, Pageable pageable) {
        return stashLineRepo.findByStashId(stashId, pageable)
                .map(s -> new ItemsInStashDto(
                        s.getItem().getId(),
                        s.getItem().getName(),
                        s.getItem().getDescription(),
                        s.getQuantity()));
    }

    public Page<ItemsInStashDto> getStashLinesBySquirrelId(Long squirrelId, Pageable pageable) {
        return stashLineRepo.findByStash_SquirrelId(squirrelId, pageable)
                .map(s -> new ItemsInStashDto(
                        s.getItem().getId(),
                        s.getItem().getName(),
                        s.getItem().getDescription(),
                        s.getQuantity()));
    }

    // update a stash line - return a StashLineResponseDto
    public StashLineResponseDto updateStashLine(StashLineUpdateDto updateDto) {
        // Get the stash line by id
        StashLine stashLine = stashLineRepo.findById(updateDto.getId())
                .orElseThrow(() -> new NotFoundException("Stash line not found"));

        // Set the new quantity
        stashLine.setQuantity(updateDto.getQuantity());

        // Save the stash line
        StashLine savedStashLine = stashLineRepo.save(stashLine);

        // Return the stash line dto
        return new StashLineResponseDto(
                savedStashLine.getId(),
                savedStashLine.getItem().getId(),
                savedStashLine.getStash().getId(),
                savedStashLine.getQuantity());
    }

    // delete stash line- return void
    public void deleteStashLine(Long stashLineId) {
        // Get the stash line by id
        StashLine stashLine = stashLineRepo.findById(stashLineId)
                .orElseThrow(() -> new NotFoundException("Stash line not found"));

        // Delete the stash line
        stashLineRepo.delete(stashLine);
    }


}
