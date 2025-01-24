package org.example.service_service.controller;

import org.example.service_service.ServiceService;
import org.example.service_service.entity.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/get-service")
    public Service getService(@RequestParam String name) {
        return serviceService.getServiceByName(name);
    }

    @GetMapping("/get-service-by-address")
    public Service getServiceByAddress(@RequestParam String address) {
        return serviceService.getServiceByAddress(address);
    }

    @GetMapping("/services")
    public List<Service> getServices() {
        return serviceService.getServices();
    }

    @PostMapping("/services")
    public Service addService(@RequestBody Service service) {
        return serviceService.addService(service);
    }
}