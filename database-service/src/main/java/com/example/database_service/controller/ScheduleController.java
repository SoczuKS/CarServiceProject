package com.example.database_service.controller;

import com.example.database_service.entity.Schedule;
import com.example.database_service.repository.ScheduleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {
    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping("/schedules")
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }
}
