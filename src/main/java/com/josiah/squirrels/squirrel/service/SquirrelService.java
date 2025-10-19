package com.josiah.squirrels.squirrel.service;

import com.josiah.squirrels.common.exception.NotFoundException;
import com.josiah.squirrels.squirrel.dto.SquirrelResponseDto;
import com.josiah.squirrels.squirrel.entity.Squirrel;
import com.josiah.squirrels.squirrel.repository.SquirrelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SquirrelService {

    private SquirrelRepository squirrelRepository;
    private static final Logger logger = LoggerFactory.getLogger(SquirrelService.class);

    @Autowired
    public SquirrelService(SquirrelRepository squirrelRepository) {
        this.squirrelRepository = squirrelRepository;
    }

    public Page<SquirrelResponseDto> getSquirrels(Pageable pageable) {
        Page<Squirrel> squirrels = squirrelRepository.findAll(pageable);
        Page<SquirrelResponseDto> squirrelResponseDtos = squirrels
                .map(s -> new SquirrelResponseDto(s.getId(), s.getName()));

        return squirrelResponseDtos;
    }

    public Optional<Squirrel> createSquirrel(String name) {
        Squirrel squirrel = new Squirrel();
        squirrel.setName(name);
        try {
            Squirrel savedSquirrel = squirrelRepository.save(squirrel);
            logger.info("Creating new squirrel with name: {}", savedSquirrel.getName());
            logger.debug("Saved squirrel: {} with ID {}", savedSquirrel.getName(), savedSquirrel.getId());
            return Optional.of(savedSquirrel);
        } catch (DataIntegrityViolationException e) {
            logger.error("Creating new squirrel failed: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public SquirrelResponseDto getSquirrelById(Long id) {

        Squirrel squirrel = squirrelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Squirrel not found"));

        return new SquirrelResponseDto(squirrel.getId(), squirrel.getName());
    }

    public SquirrelResponseDto updateSquirrelName(Long id, String name) {
        Squirrel squirrel = squirrelRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Squirrel not found"));

        squirrel.setName(name);
        Squirrel savedSquirrel = squirrelRepository.save(squirrel);

        return new SquirrelResponseDto(savedSquirrel.getId(), savedSquirrel.getName());
    }

    public Optional<Squirrel> deleteSquirrelById(Long id) {
        Optional<Squirrel> squirrel = squirrelRepository.findById(id);

        if (squirrel.isPresent()) {
            squirrelRepository.deleteById(id);
            return squirrel;
        }
        return Optional.empty();
    }
}
