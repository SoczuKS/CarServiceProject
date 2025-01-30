package com.example.schedule_service.service_client;

import com.example.dto.Commission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {

}
