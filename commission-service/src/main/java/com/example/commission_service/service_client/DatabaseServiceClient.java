package com.example.commission_service.service_client;

import com.example.dto.Commission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/commissions")
    List<Commission> getCommissions();

    @PostMapping("/commissions")
    Commission addCommission(@RequestBody Commission commission);
}
