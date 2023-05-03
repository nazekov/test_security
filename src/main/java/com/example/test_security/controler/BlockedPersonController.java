package com.example.test_security.controler;

import com.example.test_security.model.BlockedPerson;
import com.example.test_security.service.BlockedService;
import com.example.test_security.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/black-list")
public class BlockedPersonController {

    private final BlockedService blockedService;

    private final PersonService personService;

    public BlockedPersonController(BlockedService blockedService,
                                   PersonService personService) {
        this.blockedService = blockedService;
        this.personService = personService;
    }

    @GetMapping("/ban-form/{personId}")
    public String getBanForm(@PathVariable Long personId, Model model) {
        BlockedPerson blockedPerson = BlockedPerson.builder()
                .requisite(personService.findById(personId).getRequisite())
                .build();
        model.addAttribute("blockedPerson", blockedPerson);
        return "ban-form";
    }

    @PostMapping("/{email}/ban")
    public String ban(@ModelAttribute BlockedPerson blockedPerson,
                      @PathVariable String email) {
        blockedService.ban(blockedPerson, email);
        return "redirect:/admin";
    }

    @GetMapping("/{email}/unban/{personId}")
    public String unban(@PathVariable Long personId,
                      @PathVariable String email) {
        System.out.println("personId " + personId + " ----username---- " + email);
        blockedService.update(personId, email);
        return "redirect:/admin";
    }
}
