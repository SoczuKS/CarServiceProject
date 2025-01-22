package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDTO {

    private int id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}