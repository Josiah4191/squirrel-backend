package com.josiah.squirrels.item.repository;

import com.josiah.squirrels.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> { }
