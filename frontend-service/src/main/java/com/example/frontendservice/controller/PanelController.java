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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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
        model.addAttribute("employeesNoAdmin", userServiceClient.getEmployees().stream().filter(e -> e.getRole() != Role.ADMIN).toList());
        model.addAttribute("newEmployee", new User());

        List<Service> services = serviceServiceClient.getServices();
        for (Service service : services) {
            List<Integer> userIds = service.getEmployeesIDs();
            List<User> users = userIds.stream()
                    .map(userServiceClient::getUserById)
                    .collect(Collectors.toList());
            service.setEmployees(users);
        }

        model.addAttribute("services", services);
        model.addAttribute("newService", new Service());

        model.addAttribute("items", inventoryServiceClient.getItems());
        model.addAttribute("newItem", new Item());

        List<User> clients = userServiceClient.getClients();
        model.addAttribute("individualClients", clients.stream().filter(c -> c.getTIN() == null).toList());
        model.addAttribute("newIndividualClient", new User());
        model.addAttribute("companyClients", clients.stream().filter(c -> c.getTIN() != null).toList());
        model.addAttribute("newCompanyClient", new User());

        model.addAttribute("cars", carServiceClient.getCars(userId));
        model.addAttribute("newCar", new Car());

        return "index";
    }

    @PostMapping("/add_employee")
    public String addUser(@ModelAttribute User employee) {
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        employee.setActive(true);
        userServiceClient.addUser(employee);
        return "redirect:/";
    }

    @PostMapping("/add_service")
    public String addService(@ModelAttribute Service service) {
        serviceServiceClient.addService(service);
        return "redirect:/";
    }

    @PostMapping("/add_item")
    public String addItem(@ModelAttribute Item item) {
        inventoryServiceClient.addItem(item);
        return "redirect:/";
    }

    @PostMapping("/add_individual_client")
    public String addIndividualClient(@ModelAttribute User client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        client.setActive(true);
        userServiceClient.addUser(client);
        return "redirect:/";
    }

    @PostMapping("/add_company_client")
    public String addCompanyClient(@ModelAttribute User client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        client.setActive(true);
        userServiceClient.addUser(client);
        return "redirect:/";
    }

    @PostMapping("/add_car")
    public String addCar(@ModelAttribute Car car) {
        car.setOwnerId((Integer) httpSession.getAttribute("userId"));
        carServiceClient.addCar(car);
        return "redirect:/";
    }

    @PostMapping("/assign_user_to_service")
    public String assignUserToService(@RequestParam int userId, @RequestParam int serviceId) {
        serviceServiceClient.assignUserToService(userId, serviceId);
        return "redirect:/";
    }
}
