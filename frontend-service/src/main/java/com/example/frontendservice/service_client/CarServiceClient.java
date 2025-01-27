package com.example.frontendservice.service_client;

import com.example.dto.Car;
import com.example.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarServiceClient {
    @GetMapping("/cars")
    List<Car> getAllCars();

    @PostMapping("/cars")
    void addCar(@RequestBody Car car);

    @PostMapping("/get_cars_by_owner")
    List<Car> getCarsByOwner(@RequestBody User owner);
}
