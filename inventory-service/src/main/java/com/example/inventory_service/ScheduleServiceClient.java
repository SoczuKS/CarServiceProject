package com.example.inventory_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "schedule-service", url = "${schedule.service.url}")
public interface ScheduleServiceClient {

    @GetMapping("/schedule")
    String getSchedule();
}