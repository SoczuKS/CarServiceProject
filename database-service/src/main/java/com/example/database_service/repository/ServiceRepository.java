package com.example.database_service.repository;

import com.example.database_service.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Workshop, Long> {
}
