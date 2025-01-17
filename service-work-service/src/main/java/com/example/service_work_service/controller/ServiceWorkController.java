package com.example.service_work_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceWorkController {
    private final RestTemplate restTemplate;

    public ServiceWorkController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/call-service-work-service")
    public String callUserService() {
        return restTemplate.getForObject("http://service-work-service/api/", String.class);
    }
}
