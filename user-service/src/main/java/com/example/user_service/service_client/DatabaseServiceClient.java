package com.example.user_service.service_client;

import com.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/users")
    List<User> getUsers();

    @PostMapping("/users")
    User addUser(User user);

    @GetMapping("/get_user_by_email")
    User getUserByEmail(String email);
}
