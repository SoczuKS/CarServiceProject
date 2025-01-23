package com.example.inventory_service.controller;

import com.example.inventory_service.ScheduleServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final ScheduleServiceClient scheduleServiceClient;

    public InventoryController(ScheduleServiceClient scheduleServiceClient) {
        this.scheduleServiceClient = scheduleServiceClient;
    }

    @GetMapping("/check")
    public String checkInventory() {
        String scheduleServiceResponse = scheduleServiceClient.getSchedule();
        return "Inventory is available. Schedule Service Response: " + scheduleServiceResponse;
    }
}