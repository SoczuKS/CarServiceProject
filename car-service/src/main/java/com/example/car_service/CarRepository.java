package com.example.car_service;

import com.example.car_service.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByModel(String model);
    boolean existsByModel(String model);
    List<Car> findByOwnerId(int userId);
}
