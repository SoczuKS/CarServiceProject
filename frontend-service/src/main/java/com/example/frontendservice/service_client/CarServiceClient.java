package com.example.frontendservice.service_client;

import com.example.dto.Car;
import com.example.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarServiceClient {
    @GetMapping("/cars")
    List<Car> getAllCars();

    @PostMapping("/cars")
    Car addCar(@RequestBody Car car);

    @GetMapping("/car/{id}")
    Car getCarById(@PathVariable("id") int id);

    @PostMapping("/get_cars_by_owner")
    List<Car> getCarsByOwner(@RequestBody User owner);
}
