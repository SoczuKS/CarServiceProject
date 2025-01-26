package com.example.frontendservice.service_client;

import com.dto.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "inventory-service", url = "http://localhost:8762")
public interface InventoryServiceClient {
    @GetMapping("/items")
    List<Item> getItems();

    @PostMapping("/items")
    Item addItem(@RequestBody Item item);
}
