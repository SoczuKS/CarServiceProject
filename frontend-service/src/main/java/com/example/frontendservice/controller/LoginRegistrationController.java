package com.example.frontendservice.controller;

import com.dto.Role;
import com.dto.User;
import com.example.frontendservice.CustomUserDetails;
import com.example.frontendservice.service_client.UserServiceClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginRegistrationController {
    private final UserServiceClient userServiceClient;

    public LoginRegistrationController(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.CLIENT);
        if (user.getTIN().isEmpty()) {
            user.setFirstName(user.getFirstName().substring(0, user.getFirstName().length() - 1));
            user.setTIN(null);
        }
        if (user.getLastName().isEmpty()) {
            user.setFirstName(user.getFirstName().substring(1));
            user.setLastName(null);
        }
        userServiceClient.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/storeUser")
    public String storeUser(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            session.setAttribute("userId", userDetails.getId());
            session.setAttribute("user", userDetails.getUser());
        }
        return "redirect:/";
    }
}
