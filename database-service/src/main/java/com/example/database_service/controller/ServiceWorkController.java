package com.example.database_service.controller;

import com.example.database_service.entity.ServiceWork;
import com.example.database_service.repository.ServiceWorkRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceWorkController {
    private final ServiceWorkRepository serviceWorkRepository;

    public ServiceWorkController(ServiceWorkRepository serviceWorkRepository) {
        this.serviceWorkRepository = serviceWorkRepository;
    }

    @GetMapping("/service_works")
    public List<ServiceWork> getServiceWorks() {
        return serviceWorkRepository.findAll();
    }
}
