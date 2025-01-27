package com.example.car_service.controller;

import com.dto.Car;
import com.dto.User;
import com.example.car_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get_cars_by_owner")
    public List<Car> getCarsByOwner(@RequestParam("owner") User owner) {
        return databaseServiceClient.getCarsByOwner(owner);
    }
}
