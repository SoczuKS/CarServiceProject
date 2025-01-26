package com.example.frontendservice.service_client;

import com.dto.CarDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8767")
public interface CarServiceClient {
    @GetMapping("/user_cars")
    List<CarDTO> getCars(@RequestParam("userId") Integer userId);

    @PostMapping("/cars")
    void addCar(@RequestBody CarDTO car);
}
