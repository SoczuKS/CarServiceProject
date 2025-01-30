package com.example.schedule_service.client;

import com.example.dto.Commission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "commission-service")
public interface CommissionServiceClient {

    @PostMapping("/commissions")
    void addCommission(@RequestBody Commission commission);
}
