package com.example.service_work_service.client;

import com.example.service_work_service.dto.ScheduleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "schedule-service", url = "${schedule.service.url}")
public interface ScheduleClient {

    @GetMapping("/schedules/{id}")
    ScheduleDTO getScheduleById(@PathVariable("id") int id);
}