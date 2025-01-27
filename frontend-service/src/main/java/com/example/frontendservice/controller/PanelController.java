package com.example.frontendservice.controller;

import com.example.dto.*;
import com.example.frontendservice.service_client.CarServiceClient;
import com.example.frontendservice.service_client.InventoryServiceClient;
import com.example.frontendservice.service_client.UserServiceClient;
import com.example.frontendservice.service_client.WorkshopServiceClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class PanelController {
    private final UserServiceClient userServiceClient;
    private final WorkshopServiceClient workshopServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final CarServiceClient carServiceClient;
    private final HttpSession httpSession;

    public PanelController(
            UserServiceClient userServiceClient,
            WorkshopServiceClient workshopServiceClient,
            InventoryServiceClient inventoryServiceClient,
            CarServiceClient carServiceClient,
            HttpSession httpSession) {
        this.userServiceClient = userServiceClient;
        this.workshopServiceClient = workshopServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.carServiceClient = carServiceClient;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "page", required = false) String page, Model model) {
        User loggedInUser = (User) httpSession.getAttribute("user");

        List<User> allUsers = userServiceClient.getUsers();
        List<User> employees = allUsers.stream().filter(u -> u.getRole() != Role.CLIENT).toList();
        List<User> clients = allUsers.stream().filter(u -> u.getRole() == Role.CLIENT).toList();

        model.addAttribute("roles", Role.values());
        model.addAttribute("employees", employees);
        model.addAttribute("newEmployee", new User());

        model.addAttribute("employeesNoAdmin", employees.stream().filter(e -> e.getRole() != Role.ADMIN).toList());

        return "index";
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        List<User> employees = userServiceClient.getUsers().stream().filter(u -> u.getRole() != Role.CLIENT).toList();
        model.addAttribute("employees", employees);
        model.addAttribute("newEmployee", new User());
        model.addAttribute("roles", Arrays.stream(Role.values()).filter(r -> r != Role.CLIENT).toList());
        return "employees";
    }

    @GetMapping("/workshops")
    public String workshops(Model model) {
        List<Workshop> workshops = workshopServiceClient.getWorkshops();
        model.addAttribute("workshops", workshops);
        model.addAttribute("newWorkshop", new Workshop());
        return "workshops";
    }

    @GetMapping("/inventory")
    public String inventory(Model model) {
        List<Item> items = inventoryServiceClient.getItems();
        model.addAttribute("items", items);
        model.addAttribute("newItem", new Item());
        return "inventory";
    }

    @GetMapping("/individual_clients")
    public String individualClients(Model model) {
        List<User> clients = userServiceClient.getUsers().stream().filter(u -> u.getRole() == Role.CLIENT && u.getTIN() == null).toList();
        model.addAttribute("individualClients", clients);
        model.addAttribute("newIndividualClient", new User());
        return "individual_clients";
    }

    @GetMapping("/company_clients")
    public String companyClients(Model model) {
        List<User> clients = userServiceClient.getUsers().stream().filter(u -> u.getRole() == Role.CLIENT && u.getTIN() != null).toList();
        model.addAttribute("companyClients", clients);
        model.addAttribute("newCompanyClient", new User());
        return "company_clients";
    }

    @GetMapping("/cars")
    public String cars(Model model) {
        User loggedInUser = (User) httpSession.getAttribute("user");
        List<Car> cars = carServiceClient.getCarsByOwner(loggedInUser);
        model.addAttribute("cars", cars);
        model.addAttribute("newCar", new Car());
        return "cars";
    }

    @GetMapping("/service_employee_assignment")
    public String serviceEmployeeAssignment(Model model) {
        List<User> employees = userServiceClient.getUsers().stream().filter(u -> u.getRole() != Role.CLIENT && u.getRole() != Role.ADMIN).toList();
        List<Workshop> workshops = workshopServiceClient.getWorkshops();
        model.addAttribute("employees", employees);
        model.addAttribute("workshops", workshops);
        return "service_employee_assignment";
    }

    @PostMapping("/add_employee")
    public String addUser(@ModelAttribute User employee) {
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        employee.setActive(true);
        userServiceClient.addUser(employee);
        return "redirect:/?page=employees";
    }

    @PostMapping("/add_workshop")
    public String addWorkshops(@ModelAttribute Workshop workshop) {
        workshopServiceClient.addService(workshop);
        return "redirect:/?page=workshops";
    }

    @PostMapping("/add_item")
    public String addItem(@ModelAttribute Item item) {
        inventoryServiceClient.addItem(item);
        return "redirect:/?page=inventory";
    }

    @PostMapping("/add_individual_client")
    public String addIndividualClient(@ModelAttribute User client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        client.setActive(true);
        userServiceClient.addUser(client);
        return "redirect:/?page=individual_clients";
    }

    @PostMapping("/add_company_client")
    public String addCompanyClient(@ModelAttribute User client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        client.setActive(true);
        userServiceClient.addUser(client);
        return "redirect:/?page=company_clients";
    }

    @PostMapping("/add_car")
    public String addCar(@ModelAttribute Car car) {
        car.setOwner((User) httpSession.getAttribute("user"));
        carServiceClient.addCar(car);
        return "redirect:/?page=cars";
    }
}
