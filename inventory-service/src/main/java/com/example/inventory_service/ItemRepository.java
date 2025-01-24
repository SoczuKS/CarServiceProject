package com.example.inventory_service;

import com.example.inventory_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByName(String name);
    boolean existsByName(String name);
}
