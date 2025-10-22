package com.josiah.squirrels.stashline.repository;

import com.josiah.squirrels.stashline.entity.StashLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StashLineRepository extends JpaRepository<StashLine, Long>  {
    Page<StashLine> findByStashId(Long stash_id, Pageable pageable);
    Page<StashLine> findByStash_SquirrelId(Long squirrel_id, Pageable pageable);
}
