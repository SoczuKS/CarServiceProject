package com.example.task_service.controller;

import com.example.dto.Task;
import com.example.task_service.service_client.DatabaseServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private final DatabaseServiceClient databaseServiceClient;

    public TaskController(DatabaseServiceClient databaseServiceClient) {
        this.databaseServiceClient = databaseServiceClient;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return databaseServiceClient.getTasks();
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return databaseServiceClient.addTask(task);
    }
}
