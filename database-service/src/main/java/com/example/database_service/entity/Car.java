package com.example.database_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @JoinColumn(nullable = false)
    @ManyToOne
    @JsonIgnoreProperties({"cars"})
    private User owner;

    @Column(nullable = false)
    private int year;

    @OneToMany(mappedBy = "car")
    @JsonIgnoreProperties({"car"})
    private Set<Commission> commissions;
}
