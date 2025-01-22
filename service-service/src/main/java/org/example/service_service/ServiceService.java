package org.example.service_service;

import com.dto.UserDTO;
import org.example.service_service.client.UserClient;
import org.example.service_service.entity.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceService {
    private final UserClient userClient;
    private final ServiceRepository serviceRepository;

    public ServiceService(UserClient userClient, ServiceRepository serviceRepository) {
        this.userClient = userClient;
        this.serviceRepository = serviceRepository;
    }

    public UserDTO getUser(int userId) {
        return userClient.getUserById(userId);
    }

    public Service getServiceWithUser(int serviceId) {
        Service service = fetchServiceFromDatabase(serviceId);
        List<Integer> userIds = service.getUserIDs();
        List<UserDTO> users = userIds.stream()
                .map(this::getUser)
                .collect(Collectors.toList());
        service.setUsers(users);
        return service;
    }

    private Service fetchServiceFromDatabase(int serviceId) {
        return serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));
    }
}
