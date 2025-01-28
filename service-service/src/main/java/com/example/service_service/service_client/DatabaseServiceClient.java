package com.example.service_service.service_client;

import com.example.dto.Service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/services")
    List<Service> getServices();

    @PostMapping("/services")
    Service addService(@RequestBody Service service);

    @GetMapping("/get_service_by_name")
    Service getServiceByName(@RequestParam("name") String name);
}
