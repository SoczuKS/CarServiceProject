package com.example.commission_service.controller;

import com.example.commission_service.service_client.DatabaseServiceClient;
import com.example.commission_service.service_client.ScheduleServiceClient;
import com.example.dto.Commission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommissionController {
    private final DatabaseServiceClient databaseServiceClient;
    private final ScheduleServiceClient scheduleServiceClient;

    public CommissionController(DatabaseServiceClient databaseServiceClient, ScheduleServiceClient scheduleServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
        this.scheduleServiceClient = scheduleServiceClient;
    }

    @GetMapping("/commissions")
    public List<Commission> getCommissions() {
        return databaseServiceClient.getCommissions();
    }

    @PostMapping("/commissions")
    public Commission addCommission(@RequestBody Commission commission) {
        return databaseServiceClient.addCommission(commission);
    }

    @PostMapping("/schedule")
    public Commission forwardCommission(@RequestBody Commission commission) {
        return scheduleServiceClient.forwardCommission(commission);
    }
}
