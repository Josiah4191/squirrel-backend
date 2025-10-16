package com.josiah.squirrels.service;

import com.josiah.squirrels.entity.Squirrel;
import com.josiah.squirrels.repository.SquirrelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquirrelService {

    private SquirrelRepository squirrelRepository;

    @Autowired
    public SquirrelService(SquirrelRepository squirrelRepository) {
        this.squirrelRepository = squirrelRepository;
    }

    public List<Squirrel> getSquirrels() {
        return squirrelRepository.findAll();
    }

    public String addSquirrel(Squirrel squirrel) {
        squirrelRepository.save(squirrel);
        return String.format("Saved %s to database", squirrel.getName());
    }

    public String getSquirrelById(Long id) {
        Optional<Squirrel> result = squirrelRepository.findById(id);

        if (result.isPresent()) {
            Squirrel squirrel = result.get();
            return String.format("Found %s in database", squirrel.getName());
        } else {
            return String.format("Could not find %s in database", id);
        }
    }


}
