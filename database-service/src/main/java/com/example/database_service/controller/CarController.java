package com.example.database_service.controller;

import com.example.database_service.entity.Car;
import com.example.database_service.entity.User;
import com.example.database_service.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @PostMapping("/cars")
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @GetMapping("/get_cars_by_owner")
    public List<Car> getCarsByUser(@RequestParam User owner) {
        return carRepository.findByOwner(owner);
    }
}
