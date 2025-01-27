package com.example.inventory_service.controller;

import com.dto.Item;
import com.example.inventory_service.client_service.DatabaseServiceClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
    DatabaseServiceClient databaseServiceClient;

    public InventoryController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return databaseServiceClient.getItems();
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        return databaseServiceClient.addItem(item);
    }
}