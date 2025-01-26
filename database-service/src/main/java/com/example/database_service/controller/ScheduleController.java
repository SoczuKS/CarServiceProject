package com.example.database_service.controller;

import com.example.database_service.entity.Schedule;
import com.example.database_service.repository.ScheduleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
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
