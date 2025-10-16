package com.josiah.squirrels.controller;

import com.josiah.squirrels.entity.Squirrel;
import com.josiah.squirrels.service.SquirrelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SquirrelController {

    private final SquirrelService squirrelService;

    @Autowired
    public SquirrelController(SquirrelService squirrelService) {
        this.squirrelService = squirrelService;
    }

    @GetMapping("/GetSquirrel")
    public String getSquirrelById(Long id) {
        return squirrelService.getSquirrelById(id);
    }

    @GetMapping("/")
    public List<Squirrel> getSquirrels() {
        return squirrelService.getSquirrels();
    }

    @PostMapping("/AddSquirrel")
    public String getSquirrels(@RequestParam String name) {
        Squirrel squirrel = new Squirrel();
        squirrel.setName(name);
        return squirrelService.addSquirrel(squirrel);
    }


}
