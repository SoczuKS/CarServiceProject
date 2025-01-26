package com.example.car_service.service_client;

import com.dto.Car;
import com.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/cars")
    List<Car> getCars();

    @PostMapping("/cars")
    Car addCar(Car car);

    @GetMapping("/get_cars_by_owner")
    List<Car> getCarsByOwner(@RequestParam User owner);
}
