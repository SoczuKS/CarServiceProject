package com.example.frontendservice.controller;

import com.dto.*;
import com.example.frontendservice.service_client.CarServiceClient;
import com.example.frontendservice.service_client.InventoryServiceClient;
import com.example.frontendservice.service_client.ServiceServiceClient;
import com.example.frontendservice.service_client.UserServiceClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PanelController {
    private final UserServiceClient userServiceClient;
    private final ServiceServiceClient serviceServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final CarServiceClient carServiceClient;
    private final HttpSession httpSession;

    public PanelController(
            UserServiceClient userServiceClient,
            ServiceServiceClient serviceServiceClient,
            InventoryServiceClient inventoryServiceClient,
            CarServiceClient carServiceClient,
            HttpSession httpSession) {
        this.userServiceClient = userServiceClient;
        this.serviceServiceClient = serviceServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.carServiceClient = carServiceClient;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(Model model) {
        Integer userId = (Integer) httpSession.getAttribute("userId");

        model.addAttribute("roles", Role.values());
        model.addAttribute("employees", userServiceClient.getEmployees());
        model.addAttribute("newEmployee", new UserDTO());

        model.addAttribute("services", serviceServiceClient.getServices());
        model.addAttribute("newService", new ServiceDTO());

        model.addAttribute("items", inventoryServiceClient.getItems());
        model.addAttribute("newItem", new ItemDTO());

        List<UserDTO> clients = userServiceClient.getClients();
        model.addAttribute("individualClients", clients.stream().filter(c -> c.getTIN() == null).toList());
        model.addAttribute("newIndividualClient", new UserDTO());
        model.addAttribute("companyClients", clients.stream().filter(c -> c.getTIN() != null).toList());
        model.addAttribute("newCompanyClient", new UserDTO());

        model.addAttribute("cars", carServiceClient.getCars(userId));
        model.addAttribute("newCar", new CarDTO());

        return "index";
    }

    @PostMapping("/add_employee")
    public String addUser(@ModelAttribute UserDTO employee) {
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        employee.setActive(true);
        userServiceClient.addUser(employee);
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

    @PostMapping("/add_individual_client")
    public String addIndividualClient(@ModelAttribute UserDTO client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        client.setActive(true);
        userServiceClient.addUser(client);
        return "redirect:/";
    }

    @PostMapping("/add_company_client")
    public String addCompanyClient(@ModelAttribute UserDTO client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        client.setActive(true);
        userServiceClient.addUser(client);
        return "redirect:/";
    }

    @PostMapping("/add_car")
    public String addCar(@ModelAttribute CarDTO car) {
        car.setOwnerId((Integer) httpSession.getAttribute("userId"));
        carServiceClient.addCar(car);
        return "redirect:/";
    }
}
