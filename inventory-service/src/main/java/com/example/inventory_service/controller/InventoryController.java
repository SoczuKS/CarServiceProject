package com.example.inventory_service.controller;

import com.example.inventory_service.ItemService;
import com.example.inventory_service.ScheduleServiceClient;
import com.example.inventory_service.entity.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final ScheduleServiceClient scheduleServiceClient;
    ItemService itemService;

    public InventoryController(ScheduleServiceClient scheduleServiceClient) {
        this.scheduleServiceClient = scheduleServiceClient;
    }

    @GetMapping("/check")
    public String checkInventory() {
        String scheduleServiceResponse = scheduleServiceClient.getSchedule();
        return "Inventory is available. Schedule Service Response: " + scheduleServiceResponse;
    }

    @GetMapping("/get-item")
    public Item getItem(@RequestParam String name) {
        return itemService.getItemByName(name);
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @PostMapping("/items")
    public Item addItem() {
        return itemService.addItem(new Item());
    }
}