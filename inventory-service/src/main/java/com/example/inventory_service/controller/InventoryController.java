package com.example.inventory_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class InventoryController {

    private final RestTemplate restTemplate;

    public InventoryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/call-inventory-service")
    public String callUserService() {
        return restTemplate.getForObject("http://inventory-service/api/item", String.class);
    }
}
