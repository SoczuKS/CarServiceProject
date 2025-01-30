package com.example.database_service.controller;

import com.example.database_service.entity.Task;
import com.example.database_service.repository.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
}
