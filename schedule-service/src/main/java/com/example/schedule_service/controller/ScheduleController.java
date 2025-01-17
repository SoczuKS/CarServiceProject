package com.example.schedule_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ScheduleController {
    private final RestTemplate restTemplate;

    public ScheduleController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/call-schedule-service")
    public String callUserService() {
        return restTemplate.getForObject("http://schedule-service/api/", String.class);
    }
}
