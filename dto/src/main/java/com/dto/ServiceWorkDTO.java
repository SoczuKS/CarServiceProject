package com.dto;

import java.time.LocalDateTime;

public class ServiceWorkDTO {
    private int id;
    private String description;
    private Double cost;
    private LocalDateTime workDate;
    private WorkStatus status;
    private int scheduleId;
    private ScheduleDTO schedule;
}
