package com.example.test_security.controler;

import com.example.test_security.enums.Role;
import com.example.test_security.enums.Status;
import com.example.test_security.model.Person;
import com.example.test_security.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String getAdminPanel(Model model) {
        System.out.println("public String getAdminPanel() {");
        List<Person> personList = adminService.findAllPerson();
        model.addAttribute("personList", personList);
        return "admin-panel";
    }

    @PostMapping("/add")
    public String addPerson(@RequestParam Map<String, String> data) {
        Person person = Person.builder()
                        .name(data.get("name"))
                        .role(Role.valueOf(data.get("role")))
                        .password(data.get("password"))
                        .email(data.get("email"))
                        .status(Status.ACTIVE)
                        .build();

        System.out.println("Map<String, String> " + data);
        adminService.add(person);
        return "redirect:/admin";
    }
}
