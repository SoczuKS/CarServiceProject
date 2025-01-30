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

    @OneToMany(mappedBy = "commission")
    @JsonIgnoreProperties({"commission"})
    private Set<CommissionService> services;

    @Column(nullable = false)
    private WorkStatus status;

    @Column(nullable = false)
    private LocalDateTime commissionedAt;

    private LocalDateTime finishedAt;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"commissions"})
    private Car car;

    @ManyToOne
    @JsonIgnoreProperties({"employees"})
    private Workshop workshop;

    @ManyToOne
    @JsonIgnoreProperties({"commissions"})
    private User mechanic;

    @PrePersist
    protected void onCreate() {
        this.commissionedAt = LocalDateTime.now();
    }
}
