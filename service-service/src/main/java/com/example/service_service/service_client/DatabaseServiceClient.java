package com.example.service_service.service_client;

import com.example.dto.Service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/services")
    List<Service> getServices();

    @GetMapping("/get_service_by_name")
    Service getServiceByName(@RequestParam("name") String name);

    @GetMapping("/service/{id}")
    Service getServiceById(@PathVariable("id") int id);

    @PostMapping("/services")
    Service addService(@RequestBody Service service);
}
