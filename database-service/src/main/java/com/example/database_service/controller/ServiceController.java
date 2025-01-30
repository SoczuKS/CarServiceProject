package com.example.database_service.controller;

import com.example.database_service.entity.Service;
import com.example.database_service.entity.Task;
import com.example.database_service.repository.ServiceRepository;
import com.example.database_service.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/get_service_by_name")
    public Service getServiceByName(@RequestParam("name") String name) {
        return serviceRepository.findByName(name);
    }

    @GetMapping("/service/{id}")
    public Service getServiceById(@PathVariable("id") int id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @PostMapping("/services")
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }
}
