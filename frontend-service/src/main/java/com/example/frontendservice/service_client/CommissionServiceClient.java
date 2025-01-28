package com.example.frontendservice.service_client;

import com.example.dto.Commission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "commission-service")
public interface CommissionServiceClient {
    @GetMapping("/commissions")
    List<Commission> getCommissions();

    @PostMapping("/commissions")
    void addCommission(Commission commission);
}
