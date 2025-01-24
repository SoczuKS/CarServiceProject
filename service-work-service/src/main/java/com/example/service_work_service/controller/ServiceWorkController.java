package com.example.service_work_service.controller;

import com.example.service_work_service.ServiceWorkService;
import com.example.service_work_service.entity.ServiceWork;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceWorkController {
    ServiceWorkService serviceWorkService;

    @GetMapping("/call-service-work-service")
    public String callServiceWorkService() {
        return "Service work service response";
    }

    @GetMapping("/get-service-work")
    public ServiceWork getServiceWork(@RequestParam int id) {
        return serviceWorkService.getServiceWorkById(id);
    }

    @GetMapping("get-service-work-by-name")
    public ServiceWork getServiceWorkByName(@RequestParam String name) {
        return serviceWorkService.getServiceWorkByName(name);
    }

    @GetMapping("service-works")
    public List<ServiceWork> getServiceWorks() {
        return serviceWorkService.getServiceWorks();
    }

    @PostMapping("service-works")
    public ServiceWork addServiceWork(ServiceWork serviceWork) {
        return serviceWorkService.addServiceWork(serviceWork);
    }
}