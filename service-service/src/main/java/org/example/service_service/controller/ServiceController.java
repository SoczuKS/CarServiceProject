package org.example.service_service.controller;

import com.example.dto.Service;
import org.example.service_service.service_client.DatabaseServiceClient;
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
}