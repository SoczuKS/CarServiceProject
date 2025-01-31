package com.example.database_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commission_services", uniqueConstraints = {
        @UniqueConstraint(name = "commission_service", columnNames = {"commission_id", "service_id"})
})
@Getter
@Setter
public class CommissionService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "commission_id")
    @JsonIgnoreProperties("services")
    private Commission commission;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonIgnoreProperties("commissions")
    private Service service;

    @Enumerated(EnumType.STRING)
    private WorkStatus status;
}
