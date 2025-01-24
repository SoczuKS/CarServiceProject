package com.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDTO {
    private Long id;
    private String name;
    private float price;
    private int quantity;
}
