package com.example.schedule_service;

import com.example.schedule_service.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findByName(String name);
    boolean existsByName(String name);
}
