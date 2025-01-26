package com.example.database_service.repository;

import com.example.database_service.entity.ServiceWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceWorkRepository extends JpaRepository<ServiceWork, Integer> {
    ServiceWork findByName(String name);
    boolean existsByName(String name);
}