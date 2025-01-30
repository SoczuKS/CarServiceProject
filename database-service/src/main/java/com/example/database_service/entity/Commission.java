package com.example.database_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"commission"}, allowSetters = true)
    private Set<CommissionService> services;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    @Column(nullable = false)
    private LocalDateTime commissionedAt;

    private LocalDateTime finishedAt;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties(value = {"commissions"}, allowSetters = true)
    private Car car;

    @ManyToOne
    @JsonIgnoreProperties(value = {"employees"}, allowSetters = true)
    private Workshop workshop;

    @ManyToOne
    @JsonIgnoreProperties(value = {"commissions"}, allowSetters = true)
    private User mechanic;

    @PrePersist
    protected void onCreate() {
        this.commissionedAt = LocalDateTime.now();
    }
}
