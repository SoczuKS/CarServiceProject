package com.example.frontendservice.controller;

import com.dto.ItemDTO;
import com.dto.Role;
import com.dto.ServiceDTO;
import com.dto.UserDTO;
import com.example.frontendservice.service_client.InventoryServiceClient;
import com.example.frontendservice.service_client.ServiceServiceClient;
import com.example.frontendservice.service_client.UserServiceClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
public class HttpRequestController {
    private final UserServiceClient userServiceClient;
    private final ServiceServiceClient serviceServiceClient;
    private final InventoryServiceClient inventoryServiceClient;

    public HttpRequestController(
            UserServiceClient userServiceClient,
            ServiceServiceClient serviceServiceClient,
            InventoryServiceClient inventoryServiceClient) {
        this.userServiceClient = userServiceClient;
        this.serviceServiceClient = serviceServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("users", userServiceClient.getUsers());
        model.addAttribute("newUser", new UserDTO());

        model.addAttribute("services", serviceServiceClient.getServices());
        model.addAttribute("newService", new ServiceDTO());

        model.addAttribute("items", inventoryServiceClient.getItems());
        model.addAttribute("newItem", new ItemDTO());

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO user) {
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

    @PostMapping("/add_user")
    public String addUser(@ModelAttribute UserDTO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        userServiceClient.addUser(user);
        return "redirect:/";
    }

    @PostMapping("/add_service")
    public String addService(@ModelAttribute ServiceDTO service) {
        serviceServiceClient.addService(service);
        return "redirect:/";
    }

    @PostMapping("/add_item")
    public String addItem(@ModelAttribute ItemDTO item) {
        inventoryServiceClient.addItem(item);
        return "redirect:/";
    }
}
