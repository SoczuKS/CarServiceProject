package com.example.service_work_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceWorkController {

    @GetMapping("/call-service-work-service")
    public String callServiceWorkService() {
        return "Service work service response";
    }
}