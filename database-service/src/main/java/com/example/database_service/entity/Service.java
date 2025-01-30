package com.example.database_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float cost;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"services"}, allowSetters = true)
    private Set<CommissionService> commissions;

    @ManyToMany
    @JoinTable(
            name = "service_task",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    @JsonIgnoreProperties(value = {"services"}, allowSetters = true)
    private Set<Task> tasks;

    @PrePersist
    @PreUpdate
    private void calculateCost() {
        this.cost = tasks.stream().map(Task::getCost).reduce(0.0f, Float::sum);
    }
}
