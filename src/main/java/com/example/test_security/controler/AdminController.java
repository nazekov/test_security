package com.example.test_security.controler;

import com.example.test_security.enums.Role;
import com.example.test_security.enums.Status;
import com.example.test_security.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String getAdminPanel() {
        System.out.println("public String getAdminPanel() {");
        return "admin-panel";
    }

    @PostMapping("/add")
    public String addPerson(@RequestParam Map<String, String> data) {
        Person person = Person.builder()
                        .name(data.get("name"))
                        .role(Role.valueOf(data.get("role")))
                        .email(data.get("email"))
                        .status(Status.ACTIVE)
                        .build();
        System.out.println("Map<String, String> " + data);
        return "redirect:/admin";
    }
}
