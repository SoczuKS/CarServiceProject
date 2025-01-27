package com.example.service_work_service;

import com.example.dto.Schedule;
import com.example.service_work_service.client.ScheduleClient;
import com.example.service_work_service.entity.ServiceWork;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceWorkService {
    private final ScheduleClient scheduleClient;
    private final ServiceWorkRepository serviceWorkRepository;

    public ServiceWorkService(ScheduleClient scheduleClient, ServiceWorkRepository serviceWorkRepository) {
        this.scheduleClient = scheduleClient;
        this.serviceWorkRepository = serviceWorkRepository;
    }

    public ServiceWork getServiceWorkByName(String name) {
        return serviceWorkRepository.findByName(name);
    }

    public ServiceWork addServiceWork(ServiceWork serviceWork) {
        return serviceWorkRepository.save(serviceWork);
    }

    public List<ServiceWork> getServiceWorks() {
        return serviceWorkRepository.findAll();
    }

    public Schedule getSchedule(int scheduleId) {
        return scheduleClient.getScheduleById(scheduleId);
    }

    public ServiceWork getServiceWorkWithSchedule(int serviceWorkId) {
        ServiceWork serviceWork = fetchServiceWorkFromDatabase(serviceWorkId);

        Schedule schedule = getSchedule(serviceWork.getScheduleId());
        serviceWork.setSchedule(schedule);

        return serviceWork;
    }

    private ServiceWork fetchServiceWorkFromDatabase(int serviceWorkId) {
        return serviceWorkRepository.findById(serviceWorkId).orElseThrow(() -> new RuntimeException("ServiceWork not found"));
    }

    public ServiceWork getServiceWorkById(int id) {
        return serviceWorkRepository.findById(id).orElse(null);
    }
}