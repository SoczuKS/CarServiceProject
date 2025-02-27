package com.example.task_service.service_client;

import com.example.dto.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "database-service")
public interface DatabaseServiceClient {
    @GetMapping("/tasks")
    List<Task> getTasks();

    @PostMapping("/tasks")
    Task addTask(@RequestBody Task task);
}
