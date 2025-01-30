package com.example.database_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float cost;

    @ManyToMany(mappedBy = "tasks")
    @JsonIgnoreProperties(value = {"tasks"}, allowSetters = true)
    private Set<Service> services;
}
