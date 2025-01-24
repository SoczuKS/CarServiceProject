package com.example.schedule_service;

import com.example.schedule_service.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;


    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule getScheduleById(int id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public Schedule getScheduleByName(String name) {
        return scheduleRepository.findByName(name);
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
