package com.example.car_service.service_client;

import com.example.dto.Car;
import com.example.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/cars")
    List<Car> getCars();

    @PostMapping("/cars")
    Car addCar(@RequestBody Car car);

    @PostMapping("/get_cars_by_owner")
    List<Car> getCarsByOwner(@RequestBody User owner);

    @GetMapping("/get_car_by_id/{id}")
    Car getCarById(@PathVariable("id") int id);
}
