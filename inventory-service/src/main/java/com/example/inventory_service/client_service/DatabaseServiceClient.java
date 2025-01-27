package com.example.inventory_service.client_service;

import com.dto.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/items")
    List<Item> getItems();

    @PostMapping("/items")
    Item addItem(@RequestBody Item item);
}
