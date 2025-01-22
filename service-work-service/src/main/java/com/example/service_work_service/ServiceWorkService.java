package com.example.service_work_service;

import com.example.service_work_service.client.ScheduleClient;
import com.example.service_work_service.dto.ScheduleDTO;
import com.example.service_work_service.entity.ServiceWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkService {
    @Autowired
    private ScheduleClient scheduleClient;

    @Autowired
    private ServiceWorkRepository serviceWorkRepository;

    public ScheduleDTO getSchedule(int scheduleId) {
        return scheduleClient.getScheduleById(scheduleId);
    }

    public ServiceWork getServiceWorkWithSchedule(int serviceWorkId) {
        ServiceWork serviceWork = fetchServiceWorkFromDatabase(serviceWorkId);

        ScheduleDTO schedule = getSchedule(serviceWork.getScheduleId());
        serviceWork.setSchedule(schedule);

        return serviceWork;
    }

    private ServiceWork fetchServiceWorkFromDatabase(int serviceWorkId) {
        return serviceWorkRepository.findById(serviceWorkId).orElseThrow(() -> new RuntimeException("ServiceWork not found"));
    }
}