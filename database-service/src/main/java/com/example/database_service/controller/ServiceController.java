package com.example.database_service.controller;

import com.example.database_service.entity.Service;
import com.example.database_service.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/services")
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    @PostMapping("/services")
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

    @GetMapping("/get_service_by_name")
    public Service getServiceByName(@RequestParam("name") String name) {
        return serviceRepository.findByName(name);
    }
}
