package com.example.frontendservice.service_client;

import com.example.dto.Service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "service-service")
public interface ServiceServiceClient {
    @GetMapping("/services")
    List<Service> getServices();

    @GetMapping("/service/{id}")
    Service getServiceById(@PathVariable("id") int serviceId);

    @PostMapping("/services")
    Service addService(Service service);
}
