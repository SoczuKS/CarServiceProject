package com.example.frontendservice.service_client;

import com.example.dto.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "task-service")
public interface TaskServiceClient {
    @GetMapping("/tasks")
    List<Task> getTasks();

    @PostMapping("/tasks")
    Task addTask(Task task);
}
