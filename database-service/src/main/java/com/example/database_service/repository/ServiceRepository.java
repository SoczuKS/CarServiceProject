package com.example.database_service.repository;

import com.example.database_service.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Service findByName(String name);
}
