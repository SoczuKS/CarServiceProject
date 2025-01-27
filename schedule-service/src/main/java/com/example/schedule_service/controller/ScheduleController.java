package com.example.schedule_service.controller;

import com.example.schedule_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    private final DatabaseServiceClient databaseServiceClient;

    public ScheduleController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }
}