package com.example.service_work_service;

import com.example.service_work_service.entity.ServiceWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceWorkRepository extends JpaRepository<ServiceWork, Integer> {
}