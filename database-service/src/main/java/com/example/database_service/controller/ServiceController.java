package com.example.database_service.controller;

import com.example.database_service.entity.Workshop;
import com.example.database_service.repository.ServiceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/workshops")
    public List<Workshop> getServices() {
        return serviceRepository.findAll();
    }

    @PostMapping("/workshops")
    public Workshop addService(@RequestBody Workshop workshop) {
        return serviceRepository.save(workshop);
    }
}
