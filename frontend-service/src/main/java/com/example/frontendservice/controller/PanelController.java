package com.example.frontendservice.controller;

import com.example.dto.*;
import com.example.frontendservice.service_client.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/workshop/{id}")
    public String workshop(Model model, @PathVariable("id") int id) {
        Workshop workshop = workshopServiceClient.getWorkshopById(id);
        List<User> notAssignedEmployees =
                userServiceClient
                        .getUsers()
                        .stream()
                        .filter(u -> {
                            if (u.getRole() == Role.CLIENT) {
                                return false;
                            }
                            if (u.getRole() == Role.ADMIN) {
                                return false;
                            }
                            return u.getWorkshop() == null;
                        })
                        .toList();
        model.addAttribute("workshop", workshop);
        model.addAttribute("notAssignedEmployees", notAssignedEmployees);
        return "workshop";
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

    @GetMapping("/car/{id}")
    public String car(Model model, @PathVariable("id") int id) {
        Car car = carServiceClient.getCarById(id);
        List<Service> services = serviceServiceClient.getServices();
        List<Workshop> workshops = workshopServiceClient.getWorkshops();
        model.addAttribute("car", car);
        model.addAttribute("services", services);
        model.addAttribute("workshops", workshops);
        model.addAttribute("newCommission", new Commission());
        return "car";
    }

    @GetMapping("/commissions")
    public String commissions(Model model) {
        List<Commission> commissions = commissionServiceClient.getCommissions();
        model.addAttribute("commissions", commissions);
        model.addAttribute("workStatuses", WorkStatus.values());
        return "commissions";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        List<Task> tasks = taskServiceClient.getTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @GetMapping("/services")
    public String services(Model model) {
        List<Service> services = serviceServiceClient.getServices();
        List<Task> tasks = taskServiceClient.getTasks();
        model.addAttribute("allTasks", tasks);
        model.addAttribute("services", services);
        model.addAttribute("newService", new Service());
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

        if (workshop == null) {
            return "redirect:/workshops";
        }
        return "redirect:/workshop/" + workshopId;
    }

    @PostMapping("/commissions")
    public String addCommission(@ModelAttribute Commission commission, @RequestParam("carId") Integer carId, @RequestParam("workshopId") Integer workshopId, @RequestParam("serviceIds") List<Integer> serviceIds) {
        Car car = carServiceClient.getCarById(carId);
        Workshop workshop = workshopServiceClient.getWorkshopById(workshopId);

        commission.setCar(car);
        commission.setWorkshop(workshop);
        commission.setStatus(WorkStatus.NOT_STARTED);
        List<Service> services = serviceIds.stream()
                .map(serviceServiceClient::getServiceById)
                .toList();
        Set<CommissionService> commissionServices = services.stream()
                .map(service -> {
                    CommissionService commissionService = new CommissionService();
                    commissionService.setService(service);
                    commissionService.setStatus(commission.getStatus());
                    return commissionService;
                })
                .collect(Collectors.toSet());
        commission.setServices(commissionServices);

        commissionServiceClient.addCommission(commission);
        return "redirect:/car/" + carId;
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute Task task) {
        taskServiceClient.addTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/services")
    public String addService(@ModelAttribute Service service, @RequestParam("taskIds") List<Integer> taskIds) {
        List<Task> tasks = taskServiceClient.getTasks().stream().filter(t -> taskIds.contains(t.getId())).toList();
        service.setTasks(new HashSet<>(tasks));
        serviceServiceClient.addService(service);
        return "redirect:/services";
    }
}
