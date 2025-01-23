package com.example.schedule_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    @GetMapping("/call-schedule-service")
    public String callScheduleService() {
        return "Schedule service response";
    }
}