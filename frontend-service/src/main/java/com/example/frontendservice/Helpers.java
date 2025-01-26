package com.example.frontendservice;

import com.dto.UserDTO;

public class Helpers {
    public static void adjustNewClientData(UserDTO user) {
        if (user.getTIN().isEmpty()) {
            user.setFirstName(user.getFirstName().substring(0, user.getFirstName().length() - 1));
            user.setTIN(null);
        }
        if (user.getLastName().isEmpty()) {
            user.setFirstName(user.getFirstName().substring(1));
            user.setLastName(null);
        }
    }
}
