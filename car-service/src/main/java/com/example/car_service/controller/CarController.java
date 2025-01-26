package com.example.car_service.controller;

import com.dto.UserDTO;
import com.example.car_service.CarService;
import com.example.car_service.entity.Car;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car")
    public Car getCar(@RequestParam int id) {
        return carService.getCarById(id);
    }

    @GetMapping("/car_by_model")
    public Car getCarByModel(@RequestParam String model) {
        return carService.getCarByModel(model);
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @PostMapping("/get_owner")
    public UserDTO getOwner(@RequestParam int ownerId) {
        return carService.getOwner(ownerId);
    }

    @GetMapping("/user_cars")
    public List<Car> getUserCars(@RequestParam int userId) {
        return carService.getUserCars(userId);
    }
}
