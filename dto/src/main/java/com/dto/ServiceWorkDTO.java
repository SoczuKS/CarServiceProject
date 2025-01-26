package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceWorkDTO {
    private int id;
    private String name;
    private String description;
    private Double cost;
    private LocalDateTime workDate;
    private WorkStatus status;
    private int scheduleId;
    private ScheduleDTO schedule;
}
