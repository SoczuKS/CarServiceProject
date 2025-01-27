package com.example.service_work_service.controller;

import com.example.service_work_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceWorkController {
    DatabaseServiceClient databaseServiceClient;

    public ServiceWorkController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }
}