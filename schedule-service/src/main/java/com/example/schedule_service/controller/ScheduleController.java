package com.example.schedule_service.controller;

import com.example.schedule_service.ScheduleService;
import com.example.schedule_service.entity.Schedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/call-schedule-service")
    public String callScheduleService() {
        return "Schedule service response";
    }

    @GetMapping("get-schedule")
    public Schedule getSchedule(@RequestParam int id) {
        return scheduleService.getScheduleById(id);
    }

    @GetMapping("get-schedule-by-name")
    public Schedule getScheduleByName(@RequestParam String name) {
        return scheduleService.getScheduleByName(name);
    }

    @GetMapping("schedules")
    public List<Schedule> getSchedules() {
        return scheduleService.getSchedules();
    }

    @PostMapping("schedules")
    public Schedule addSchedule(Schedule schedule) {
        return scheduleService.addSchedule(schedule);
    }
}