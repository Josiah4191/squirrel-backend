package com.josiah.squirrels.squirrel.repository;


import com.josiah.squirrels.squirrel.entity.Squirrel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SquirrelRepository extends JpaRepository<Squirrel, Long> {}
