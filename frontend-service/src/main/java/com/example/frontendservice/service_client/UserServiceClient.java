package com.example.frontendservice.service_client;

import com.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/get_user_by_email")
    User getUserByEmail(@RequestParam String email);

    @GetMapping("/users")
    List<User> getUsers();

    @PostMapping("/users")
    User addUser(@RequestBody User user);
}
