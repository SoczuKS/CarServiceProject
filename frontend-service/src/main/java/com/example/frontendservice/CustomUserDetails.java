package com.example.frontendservice;

import com.dto.UserDTO;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.List;

@Getter
public class CustomUserDetails extends User {
    private final UserDTO user;

    public CustomUserDetails(UserDTO user) {
        super(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }

}
