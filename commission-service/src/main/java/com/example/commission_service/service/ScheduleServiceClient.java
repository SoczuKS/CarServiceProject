package com.example.commission_service.service;

import com.example.dto.Commission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "schedule-service")
public interface ScheduleServiceClient {

    @PostMapping("/schedule")
    Commission forwardCommission(@RequestBody Commission commission);
}
