package com.example.database_service.controller;

import com.example.database_service.entity.Workshop;
import com.example.database_service.repository.WorkshopRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/workshop/{id}")
    public Workshop getServiceById(@PathVariable("id") Long id) {
        return workshopRepository.findById(id).orElse(null);
    }

    @PostMapping("/workshops")
    public Workshop addService(@RequestBody Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @GetMapping("/get_workshop_by_id/{id}")
    public Workshop getServiceById(@PathVariable("id") int id) {
        return workshopRepository.findById(id).orElse(null);
    }
}
