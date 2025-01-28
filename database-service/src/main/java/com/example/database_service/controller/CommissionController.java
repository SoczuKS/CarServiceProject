package com.example.database_service.controller;

import com.example.database_service.entity.Commission;
import com.example.database_service.repository.CommissionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommissionController {
    private final CommissionRepository commissionRepository;

    public CommissionController(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }

    @GetMapping("/commissions")
    public List<Commission> getCommissions() {
        return commissionRepository.findAll();
    }

    @PostMapping("/commissions")
    public Commission addCommission(@RequestBody Commission commission) {
        return commissionRepository.save(commission);
    }
}
