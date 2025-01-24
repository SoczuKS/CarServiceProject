package com.example.frontendservice.service_client;

import com.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8763")
public interface UserServiceClient {
    @GetMapping("/get_user")
    UserDTO getUserByEmail(@RequestParam("email") String email);

    @GetMapping("/users")
    List<UserDTO> getUsers();

    @PostMapping("/users")
    UserDTO addUser(@RequestBody UserDTO user);
}
