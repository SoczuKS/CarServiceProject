package com.example.database_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "service")
    @JsonIgnoreProperties({"services"})
    private Set<CommissionService> commissions;

    @ManyToMany
    @JoinTable(
            name = "service_task",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    @JsonIgnoreProperties({"services"})
    private Set<Task> tasks;

    @PrePersist
    @PreUpdate
    private void calculateCost() {
        this.cost = tasks.stream().map(Task::getCost).reduce(0.0f, Float::sum);
    }
}
