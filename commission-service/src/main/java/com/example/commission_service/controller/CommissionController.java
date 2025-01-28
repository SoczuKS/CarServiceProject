package com.example.commission_service.controller;

import com.example.dto.Commission;
import com.example.commission_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommissionController {
    private final DatabaseServiceClient databaseServiceClient;

    public CommissionController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }

    @GetMapping("/commissions")
    public List<Commission> getCommissions() {
        return databaseServiceClient.getCommissions();
    }

    @PostMapping("/commissions")
    public Commission addCommission(@RequestBody Commission commission) {
        return databaseServiceClient.addCommission(commission);
    }
}
