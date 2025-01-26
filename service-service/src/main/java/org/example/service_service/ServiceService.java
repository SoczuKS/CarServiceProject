package org.example.service_service;

import com.dto.UserDTO;
import org.example.service_service.client.UserClient;
import org.example.service_service.entity.Service;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceService {
    private final UserClient userClient;
    private final ServiceRepository serviceRepository;

    public ServiceService(UserClient userClient, ServiceRepository serviceRepository) {
        this.userClient = userClient;
        this.serviceRepository = serviceRepository;
    }

    public Service getServiceByName(String name) {
        return serviceRepository.findByName(name);
    }

    public Service getServiceByAddress(String address) {
        return serviceRepository.findByAddress(address);
    }

    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    public UserDTO getUser(int userId) {
        return userClient.getUserById(userId);
    }

    public Service getServiceWithUser(int serviceId) {
        Service service = fetchServiceFromDatabase(serviceId);
        List<Integer> userIds = service.getEmployeesIDs();
        List<UserDTO> users = userIds.stream()
                .map(this::getUser)
                .collect(Collectors.toList());
        service.setEmployees(users);
        return service;
    }

    private Service fetchServiceFromDatabase(int serviceId) {
        return serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public void assignUserToService(int userId, int serviceId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        service.getEmployeesIDs().add(userId);
        serviceRepository.save(service);
    }
}
