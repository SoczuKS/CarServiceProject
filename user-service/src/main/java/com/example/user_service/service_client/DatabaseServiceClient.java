package com.example.user_service.service_client;

import com.example.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/users")
    List<User> getUsers();

    @PostMapping("/users")
    User addUser(@RequestBody User user);

    @GetMapping("/get_user_by_email")
    User getUserByEmail(@RequestParam("email") String email);
}
