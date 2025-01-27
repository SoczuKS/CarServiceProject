package com.example.database_service.controller;

import com.example.database_service.entity.Item;
import com.example.database_service.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }
}
