package com.example.test_security.controler;

import com.example.test_security.model.BlockedRequisite;
import com.example.test_security.service.BlockedRequisiteService;
import com.example.test_security.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/black-list")
public class BlockedRequisiteController {

    private final BlockedRequisiteService blockedRequisiteService;

    private final PersonService personService;

    public BlockedRequisiteController(BlockedRequisiteService blockedRequisiteService,
                                      PersonService personService) {
        this.blockedRequisiteService = blockedRequisiteService;
        this.personService = personService;
    }

    @GetMapping
    public String getBanForm(Model model) {
//        BlockedRequisite blockedRequisite = BlockedRequisite.builder()
//                .requisite(personService.findById(personId).getRequisite())
//                .build();
        model.addAttribute("blockedRequisite", new BlockedRequisite());
        return "admin-dir/ban-panel";
    }

    @PostMapping("/{username}/ban")
    public String ban(@ModelAttribute BlockedRequisite blockedRequisite,
                        @PathVariable String username) {
//        blockedRequisiteService.ban(blockedRequisite, username);
        return "redirect:/admin";
    }

    @PostMapping("/{username}/ban/{requisite}")
    public String ban(/*@ModelAttribute BlockedRequisite blockedRequisite,*/
                        @PathVariable Long requisite,
                        @PathVariable String username) {
//        blockedRequisiteService.ban(blockedRequisite, username);
        return "redirect:/admin";
    }

    @GetMapping("/{username}/unban/{requisite}")
    public String unban(@PathVariable Long requisite,
                      @PathVariable String username) {
        System.out.println("requisite " + requisite + " ----username---- " + username);
//        blockedRequisiteService.update(personId, username);
        return "redirect:/admin";
    }
}
