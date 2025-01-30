package org.example.workshop_service.controller;

import com.example.dto.Workshop;
import org.example.workshop_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get_workshop_by_id/{id}")
    public Workshop getServiceById(@PathVariable("id") int id) {
        return databaseServiceClient.getWorkshopById(id);
    }
}