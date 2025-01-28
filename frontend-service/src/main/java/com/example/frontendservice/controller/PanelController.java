package com.example.frontendservice.controller;

import com.example.dto.*;
import com.example.frontendservice.service_client.*;
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
    private final CommissionServiceClient commissionServiceClient;
    private final TaskServiceClient taskServiceClient;
    private final ServiceServiceClient serviceServiceClient;
    private final HttpSession httpSession;

    public PanelController(
            UserServiceClient userServiceClient,
            WorkshopServiceClient workshopServiceClient,
            InventoryServiceClient inventoryServiceClient,
            CarServiceClient carServiceClient,
            CommissionServiceClient commissionServiceClient,
            TaskServiceClient taskServiceClient,
            ServiceServiceClient serviceServiceClient,
            HttpSession httpSession) {
        this.userServiceClient = userServiceClient;
        this.workshopServiceClient = workshopServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.carServiceClient = carServiceClient;
        this.commissionServiceClient = commissionServiceClient;
        this.taskServiceClient = taskServiceClient;
        this.serviceServiceClient = serviceServiceClient;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index() {
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

    @GetMapping("/workshop_employee_assignment")
    public String serviceEmployeeAssignment(Model model) {
        List<User> employees = userServiceClient.getUsers().stream().filter(u -> u.getRole() != Role.CLIENT && u.getRole() != Role.ADMIN).toList();
        List<Workshop> workshops = workshopServiceClient.getWorkshops();
        model.addAttribute("employees", employees);
        model.addAttribute("workshops", workshops);
        return "workshop_employee_assignment";
    }

    @GetMapping("/commissions")
    public String commissions(Model model) {
        List<Commission> commissions = commissionServiceClient.getCommissions();
        model.addAttribute("commissions", commissions);
        return "commissions";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        List<Task> tasks = taskServiceClient.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/services")
    public String services(Model model) {
        List<Service> services = serviceServiceClient.getServices();
        model.addAttribute("services", services);
        return "services";
    }

    @PostMapping("/add_employee")
    public String addUser(@ModelAttribute User employee) {
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        employee.setActive(true);
        userServiceClient.addUser(employee);
        return "redirect:/employees";
    }

    @PostMapping("/add_workshop")
    public String addWorkshops(@ModelAttribute Workshop workshop) {
        workshopServiceClient.addService(workshop);
        return "redirect:/workshops";
    }

    @PostMapping("/add_item")
    public String addItem(@ModelAttribute Item item) {
        inventoryServiceClient.addItem(item);
        return "redirect:/inventory";
    }

    @PostMapping("/add_individual_client")
    public String addIndividualClient(@ModelAttribute User client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        client.setActive(true);
        userServiceClient.addUser(client);
        return "redirect:/individual_clients";
    }

    @PostMapping("/add_company_client")
    public String addCompanyClient(@ModelAttribute User client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        client.setActive(true);
        userServiceClient.addUser(client);
        return "redirect:/company_clients";
    }

    @PostMapping("/add_car")
    public String addCar(@ModelAttribute Car car) {
        car.setOwner((User) httpSession.getAttribute("user"));
        carServiceClient.addCar(car);
        return "redirect:/cars";
    }

    @PostMapping("/workshop_employee_assignment")
    public String assignEmployeeToWorkshop(@RequestParam("employeeId") int employeeId, @RequestParam("workshopId") int workshopId) {
        Workshop workshop = workshopServiceClient.getWorkshops().stream().filter(s -> s.getId() == workshopId).findFirst().orElse(null);
        User employee = userServiceClient.getUsers().stream().filter(u -> u.getId() == employeeId).findFirst().orElse(null);

        if (workshop != null && employee != null) {
            employee.setWorkshop(workshop);
            userServiceClient.addUser(employee);
        }

        return "redirect:/workshop_employee_assignment";
    }

    @PostMapping("/commissions")
    public String addCommission(@ModelAttribute Commission commission) {
        commissionServiceClient.addCommission(commission);
        return "redirect:/commissions";
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute Task task) {
        taskServiceClient.addTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/services")
    public String addService(@ModelAttribute Service service) {
        serviceServiceClient.addService(service);
        return "redirect:/services";
    }
}
