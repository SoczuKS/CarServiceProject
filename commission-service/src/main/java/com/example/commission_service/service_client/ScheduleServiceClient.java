package com.example.commission_service.service_client;

import com.example.dto.Commission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "schedule-service")
public interface ScheduleServiceClient {
    @PostMapping("/schedule")
    Commission schedule(@RequestBody Commission commission);
}
