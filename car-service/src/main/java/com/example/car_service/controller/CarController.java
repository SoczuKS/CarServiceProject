package com.example.car_service.controller;

import com.dto.UserDTO;
import com.example.car_service.CarService;
import com.example.car_service.entity.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/get-car")
    public Car getCar(@RequestParam int id) {
        return carService.getCarById(id);
    }

    @GetMapping("/get-car-by-model")
    public Car getCarByModel(@RequestParam String model) {
        return carService.getCarByModel(model);
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @PostMapping("/add-car")
    public Car addCar(@RequestParam String brand, @RequestParam String model, @RequestParam UserDTO owner, @RequestParam int year) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setOwner(owner);
        car.setYear(year);
        return carService.addCar(car);
    }

    @PostMapping("/get-owner")
    public UserDTO getOwner(@RequestParam int ownerId) {
        return carService.getOwner(ownerId);
    }
}
