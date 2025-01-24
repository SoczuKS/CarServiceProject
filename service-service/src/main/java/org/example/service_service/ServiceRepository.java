package org.example.service_service;

import org.example.service_service.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Service findByName(String name);
    boolean existsByName(String name);

    Service findByAddress(String address);
}
