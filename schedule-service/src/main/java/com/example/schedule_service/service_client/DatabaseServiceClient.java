package com.example.schedule_service.service_client;

import com.example.dto.Schedule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/schedules")
    List<Schedule> getSchedules();
}
