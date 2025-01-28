package com.example.database_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    @Column(nullable = false)
    private Set<Service> services;

    @Column(nullable = false)
    private WorkStatus status;

    @Column(nullable = false)
    private LocalDateTime commissionedAt;

    private LocalDateTime finishedAt;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Workshop workshop;
}
