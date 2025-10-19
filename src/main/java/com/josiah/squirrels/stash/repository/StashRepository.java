package com.josiah.squirrels.stash.repository;

import com.josiah.squirrels.stash.entity.Stash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StashRepository extends JpaRepository<Stash, Long> {
    Page<Stash> findBySquirrelId(Long id, Pageable pageable);
}
