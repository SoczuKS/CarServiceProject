package com.example.inventory_service;

import com.example.inventory_service.entity.Item;

import java.util.List;

public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getItemByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

}
