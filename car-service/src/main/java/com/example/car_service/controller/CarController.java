package com.example.car_service.controller;

import com.example.car_service.service_client.DatabaseServiceClient;
import com.example.dto.Car;
import com.example.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    private final DatabaseServiceClient databaseServiceClient;

    public CarController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return databaseServiceClient.getCars();
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        return databaseServiceClient.addCar(car);
    }

    @PostMapping("/get_cars_by_owner")
    public List<Car> getCarsByOwner(@RequestBody User owner) {
        return databaseServiceClient.getCarsByOwner(owner);
    }
}
