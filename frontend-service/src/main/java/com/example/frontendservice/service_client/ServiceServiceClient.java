package com.example.frontendservice.service_client;

import com.dto.ServiceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-service", url = "http://localhost:8766")
public interface ServiceServiceClient {
    @GetMapping("/services")
    List<ServiceDTO> getServices();

    @PostMapping("/services")
    ServiceDTO addService(@RequestBody ServiceDTO service);

    @PostMapping("/assign_user_to_service")
    void assignUserToService(@RequestParam int userId, @RequestParam int serviceId);
}
