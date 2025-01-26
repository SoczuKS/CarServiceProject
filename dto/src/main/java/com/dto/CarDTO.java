package com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {
    private int id;
    private String brand;
    private String model;
    private int ownerId;
    private int year;
}
