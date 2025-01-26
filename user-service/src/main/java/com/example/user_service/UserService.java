package com.example.user_service;

import com.example.user_service.entity.Role;
import com.example.user_service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getEmployees() {
        return userRepository.findOthersThanRole(Role.CLIENT);
    }

    public List<User> getClients() {
        return userRepository.findByRole(Role.CLIENT);
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }
}
