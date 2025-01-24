package com.example.car_service;

import com.dto.UserDTO;
import com.example.car_service.client.UserClient;
import com.example.car_service.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final UserClient userClient;

    public CarService(CarRepository carRepository, UserClient userClient) {
        this.carRepository = carRepository;
        this.userClient = userClient;
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car getCarByModel(String model) {
        return carRepository.findByModel(model);
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public UserDTO getOwner(int ownerId) {
        return userClient.getUserById(ownerId);
    }
}
