package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class Commission {
    private int id;
    private Set<CommissionService> services;
    private WorkStatus status;
    private LocalDateTime commissionedAt;
    private LocalDateTime finishedAt;
    private Car car;
    private Workshop workshop;
    private User mechanic;
}
