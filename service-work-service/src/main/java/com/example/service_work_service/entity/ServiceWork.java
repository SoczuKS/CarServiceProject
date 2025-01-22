package com.example.service_work_service.entity;

import com.example.service_work_service.dto.ScheduleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "service_work")
public class ServiceWork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double cost;

    @Column(nullable = false)
    private LocalDateTime workDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkStatus status;

    @Column(name = "schedule_id", nullable = false)
    private int scheduleId;

    @Transient
    private ScheduleDTO schedule;
}