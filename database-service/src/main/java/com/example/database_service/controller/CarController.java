package com.example.database_service.controller;

import com.example.database_service.entity.Car;
import com.example.database_service.entity.User;
import com.example.database_service.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable("id") int id) {
        return carRepository.findById(id).orElse(null);
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PostMapping("/get_cars_by_owner")
    public List<Car> getCarsByUser(@RequestBody User owner) {
        return carRepository.findByOwner(owner);
    }
}
