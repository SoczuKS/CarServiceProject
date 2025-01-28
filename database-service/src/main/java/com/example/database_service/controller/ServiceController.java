package com.example.database_service.controller;

import com.example.database_service.entity.Service;
import com.example.database_service.entity.Task;
import com.example.database_service.repository.ServiceRepository;
import com.example.database_service.repository.TaskRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ServiceController {
    private final ServiceRepository serviceRepository;
    private final TaskRepository taskRepository;

    public ServiceController(ServiceRepository serviceRepository, TaskRepository taskRepository) {
        this.serviceRepository = serviceRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/services")
    public String getServices(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        List<Task> allTasks = taskRepository.findAll();
        model.addAttribute("allTasks", allTasks);
        return "services";
    }

    @PostMapping("/services")
    public Service addService(@RequestParam String name, @RequestParam String description, @RequestParam Set<Integer> taskIds) {
        Service service = new Service();
        service.setName(name);
        service.setDescription(description);

        Set<Task> selectedTasks = new HashSet<>();
        for (Integer taskId : taskIds) {
            taskRepository.findById(taskId).ifPresent(selectedTasks::add);
        }
        service.setTasks(selectedTasks);

        return serviceRepository.save(service);
    }

    @GetMapping("/get_service_by_name")
    public Service getServiceByName(@RequestParam("name") String name) {
        return serviceRepository.findByName(name);
    }
}
