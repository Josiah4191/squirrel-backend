package com.josiah.squirrels.squirrel.repository;


import com.josiah.squirrels.squirrel.entity.Squirrel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquirrelRepository extends JpaRepository<Squirrel, Long> { }
