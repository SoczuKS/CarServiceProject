package org.example.workshop_service.service_client;

import com.example.dto.Workshop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/workshops")
    List<Workshop> getWorkshops();

    @GetMapping("/workshop/{id}")
    Workshop getWorkshopById(@PathVariable("id") int id);

    @PostMapping("/workshops")
    Workshop addWorkshops(@RequestBody Workshop workshop);
}
