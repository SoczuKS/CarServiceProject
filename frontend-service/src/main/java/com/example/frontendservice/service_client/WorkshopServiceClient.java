package com.example.frontendservice.service_client;

import com.example.dto.Workshop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "workshop-service")
public interface WorkshopServiceClient {
    @GetMapping("/workshops")
    List<Workshop> getWorkshops();

    @GetMapping("/workshop/{id}")
    Workshop getWorkshopById(@PathVariable("id") Long id);

    @PostMapping("/workshops")
    Workshop addService(@RequestBody Workshop workshop);

    @GetMapping("/get_workshop_by_id/{id}")
    Workshop getWorkshopById(@PathVariable("id") int id);
}
