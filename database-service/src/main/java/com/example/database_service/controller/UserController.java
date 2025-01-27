package com.example.database_service.controller;

import com.example.database_service.entity.User;
import com.example.database_service.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/get_user_by_email")
    public User getUserByEmail(@RequestParam("email") String email) {
        return userRepository.findByEmail(email);
    }
}
