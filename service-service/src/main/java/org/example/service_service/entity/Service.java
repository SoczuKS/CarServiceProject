package org.example.service_service.entity;

import com.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String description;

    @ElementCollection
    private List<Integer> userIDs;

    @Transient
    private List<UserDTO> users;
}