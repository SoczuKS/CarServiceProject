package com.example.service_work_service.service_client;

import com.example.dto.ServiceWork;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/service_works")
    List<ServiceWork> getServiceWorks();

    @PostMapping("/service_works")
    ServiceWork createServiceWork(@RequestBody ServiceWork serviceWork);
}
