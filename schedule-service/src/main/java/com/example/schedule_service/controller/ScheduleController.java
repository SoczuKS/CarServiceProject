package com.example.schedule_service.controller;

import com.example.schedule_service.service.MechanicAssignmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.Commission;

@RestController
public class ScheduleController {
    private final MechanicAssignmentService mechanicAssignmentService;

    public ScheduleController(MechanicAssignmentService mechanicAssignmentService) {
        this.mechanicAssignmentService = mechanicAssignmentService;
    }

    @PostMapping("/schedule")
    public Commission assignMechanicToCommission(@RequestBody Commission commission) {
        return mechanicAssignmentService.assignMechanicToCommission(commission);
    }
}