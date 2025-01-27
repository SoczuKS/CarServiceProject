package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {
    private Long id;
    private String name;
    private float price;
    private int quantity;
}
