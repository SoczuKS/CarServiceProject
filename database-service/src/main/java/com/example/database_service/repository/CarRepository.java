package com.example.database_service.repository;

import com.example.database_service.entity.Car;
import com.example.database_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByOwner(User owner);
}
