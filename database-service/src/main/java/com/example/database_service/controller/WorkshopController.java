package com.example.database_service.controller;

import com.example.database_service.entity.Workshop;
import com.example.database_service.repository.WorkshopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkshopController {
    private final WorkshopRepository workshopRepository;

    public WorkshopController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @GetMapping("/workshops")
    public List<Workshop> getServices() {
        return workshopRepository.findAll();
    }

    @PostMapping("/workshops")
    public Workshop addService(@RequestBody Workshop workshop) {
        return workshopRepository.save(workshop);
    }
}
