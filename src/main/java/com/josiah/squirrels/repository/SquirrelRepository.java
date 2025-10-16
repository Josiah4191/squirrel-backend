package com.josiah.squirrels.repository;


import com.josiah.squirrels.entity.Squirrel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquirrelRepository extends JpaRepository<Squirrel, Long> { }
