package com.example.frontendservice.service_client;

import com.dto.Service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-service")
public interface ServiceServiceClient {
    @GetMapping("/services")
    List<Service> getServices();

    @PostMapping("/services")
    Service addService(@RequestBody Service service);

    @PostMapping("/assign_user_to_service")
    void assignUserToService(@RequestParam int userId, @RequestParam int serviceId);
}
