
package com.example.schedule_service.service;

import com.example.dto.Commission;
import com.example.dto.User;
import com.example.dto.WorkStatus;
import com.example.schedule_service.client.CommissionServiceClient;
import com.example.schedule_service.client.UserServiceClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MechanicAssignmentService {
    private final UserServiceClient userServiceClient;
    private final CommissionServiceClient commissionServiceClient;

    public MechanicAssignmentService(UserServiceClient userServiceClient, CommissionServiceClient commissionServiceClient) {
        this.userServiceClient = userServiceClient;
        this.commissionServiceClient = commissionServiceClient;
    }

    public Optional<User> findAvailableMechanic(int workshopId) {
        return userServiceClient.getUsers().stream()
                .filter(user -> user.getWorkshop().getId() == workshopId)
                .filter(user -> user.getRole().name().equals("MECHANIC"))
                .filter(user -> user.getCommissions().stream()
                        .anyMatch(commission -> !commission.getStatus().equals(WorkStatus.COMPLETED)))
                .findFirst();
    }

    public Commission assignMechanicToCommission(Commission commission) {
        Optional<User> mechanic = findAvailableMechanic(commission.getWorkshop().getId());
        if (mechanic.isPresent()) {
            commission.setMechanic(mechanic.get());
            commission.setStatus(WorkStatus.IN_PROGRESS);
            commissionServiceClient.addCommission(commission);

            return commission;
        } else {
                return commission;
        }
    }
}