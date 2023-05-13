package com.example.test_security.controler;

import com.example.test_security.enums.Role;
import com.example.test_security.model.Person;
import com.example.test_security.service.AdminService;
import com.example.test_security.util.PersonValidator;
import com.example.test_security.util.ValidatePersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

//    private final ValidatePersonService validatePersonService;
    private final PersonValidator personValidator;

    public AdminController(AdminService adminService,
                           PersonValidator personValidator) {
        this.adminService = adminService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String getAdminPanel() {
        return "admin-dir/admin-panel";
    }

    @GetMapping("/add-form")
    public String getAddFormPanel(Model model) {
        System.out.println("public String getAdminPanel() {");
        List<Person> personList = adminService.findAllPerson();
        model.addAttribute("personList", personList);
        return "admin-dir/users";
    }

    @PostMapping("/add")
    public String addPerson(@RequestParam Map<String, String> data,
                                    BindingResult bindingResult, Model model) {
        Person person = Person.builder()
                        .name(data.get("name"))
                        .role(Role.valueOf(data.get("role")))
                        .password(data.get("password"))
                        .username(data.get("username"))
                        .build();

        System.out.println("Map<String, String> " + data);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            List<Person> personList = adminService.findAllPerson();
            model.addAttribute("personList", personList);
            return "admin-dir/users";
        }

//        if (validatePersonService.existsUsername(person)) {
//            return "errors/add-error";
//        }

        adminService.add(person);
        return "redirect:/admin/add-form";
    }
}
