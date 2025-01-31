package com.example.schedule_service.service;

import com.example.dto.Commission;
import com.example.dto.Role;
import com.example.dto.User;
import com.example.dto.WorkStatus;
import com.example.schedule_service.service_client.UserServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MechanicAssignmentService {
    private final UserServiceClient userServiceClient;
    private final RandomSelector randomSelector = new RandomSelector();

    public MechanicAssignmentService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public Optional<User> findAvailableMechanic(int workshopId) {
        List<User> mechanicsFromWorkshop = userServiceClient.getUsers().stream()
                .filter(user -> user.getWorkshop() != null && user.getWorkshop().getId() == workshopId && user.getRole() == Role.MECHANIC)
                .toList();
        return Optional.ofNullable(randomSelector.selectRandomFromList(mechanicsFromWorkshop));
    }

    public Commission assignMechanicToCommission(Commission commission) {
        Optional<User> mechanic = findAvailableMechanic(commission.getWorkshop().getId());
        if (mechanic.isPresent()) {
            commission.setMechanic(mechanic.get());
            commission.setStatus(WorkStatus.IN_PROGRESS);
        }
        return commission;
    }

    private static class RandomSelector {
        private static final Random random = new Random();

        public User selectRandomFromList(List<User> mechanics) {
            if (mechanics == null || mechanics.isEmpty()) {
                return null;
            }
            return mechanics.get(random.nextInt(mechanics.size()));
        }
    }
}