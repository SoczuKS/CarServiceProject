package com.example.service_service.controller;

import com.example.service_service.service_client.DatabaseServiceClient;
import com.example.dto.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {
    private final DatabaseServiceClient databaseServiceClient;

    public ServiceController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }

    @GetMapping("/services")
    public List<Service> getServices() {
        return databaseServiceClient.getServices();
    }

    @PostMapping("/services")
    public Service addService(@RequestBody Service service) {
        return databaseServiceClient.addService(service);
    }

    @GetMapping("/get_service_by_name")
    public Service getServiceByName(@RequestParam("name") String name) {
        return databaseServiceClient.getServiceByName(name);
    }

    @GetMapping("/get_service_by_id/{id}")
    public Service getServiceById(@PathVariable("id") int id) {
        return databaseServiceClient.getServiceById(id);
    }
}
