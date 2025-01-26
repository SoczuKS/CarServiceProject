package com.example.frontendservice;

import com.dto.UserDTO;
import com.example.frontendservice.service_client.UserServiceClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserServiceClient userServiceClient;

    public UserDetailsService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO user = userServiceClient.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new CustomUserDetails(user);
    }
}
