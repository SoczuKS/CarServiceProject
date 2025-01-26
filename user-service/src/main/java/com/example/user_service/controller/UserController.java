package com.example.user_service.controller;

import com.dto.User;
import com.example.user_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final DatabaseServiceClient databaseServiceClient;

    public UserController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return databaseServiceClient.getUsers();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return databaseServiceClient.addUser(user);
    }

    @GetMapping("/get_user_by_email")
    public User getUserByEmail(@RequestParam("email") String email) {
        return databaseServiceClient.getUserByEmail(email);
    }
}
