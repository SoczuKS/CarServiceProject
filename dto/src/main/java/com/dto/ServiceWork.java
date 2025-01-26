package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceWork {
    private int id;
    private String name;
    private String description;
    private float cost;
    private LocalDateTime workDate;
    private WorkStatus status;
    private Schedule schedule;
}
