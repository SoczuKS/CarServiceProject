package com.example.schedule_service.controller;

import com.example.schedule_service.service.MechanicAssignmentService;
import com.example.schedule_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.Commission;

@RestController
public class ScheduleController {
    private final DatabaseServiceClient databaseServiceClient;
    private final MechanicAssignmentService mechanicAssignmentService;

    public ScheduleController(DatabaseServiceClient databaseServiceClient, MechanicAssignmentService mechanicAssignmentService) {
        this.databaseServiceClient = databaseServiceClient;
        this.mechanicAssignmentService = mechanicAssignmentService;
    }

    @PostMapping("/schedule")
    public void forwardCommission(@RequestBody Commission commission) {
        mechanicAssignmentService.assignMechanicToCommission(commission);
    }
}