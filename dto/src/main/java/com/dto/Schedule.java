package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {
    private int id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ScheduleStatus status;
    private User employee;

}