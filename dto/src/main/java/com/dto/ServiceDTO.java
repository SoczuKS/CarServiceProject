package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private List<Integer> userIDs;
    private List<UserDTO> users;
}
