package org.example.workshop_service.controller;

import com.example.dto.Workshop;
import org.example.workshop_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkshopController {
    private final DatabaseServiceClient databaseServiceClient;

    public WorkshopController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }

    @GetMapping("/workshops")
    public List<Workshop> getServices() {
        return databaseServiceClient.getWorkshops();
    }

    @PostMapping("/workshops")
    public Workshop addService(@RequestBody Workshop workshop) {
        return databaseServiceClient.addWorkshops(workshop);
    }
}