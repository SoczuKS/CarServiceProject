package com.example.database_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "service_works")
public class ServiceWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float cost;

    @Column(nullable = false)
    private LocalDateTime workDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkStatus status;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Schedule schedule;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User mechanic;

    @ManyToMany
    private Set<Item> parts;
}