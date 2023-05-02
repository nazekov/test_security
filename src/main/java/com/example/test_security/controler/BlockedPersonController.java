package com.example.test_security.controler;

import com.example.test_security.service.BlockedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/black-list")
public class BlockedPersonController {

    private final BlockedService blockedService;

    public BlockedPersonController(BlockedService blockedService) {
        this.blockedService = blockedService;
    }

    @GetMapping("/{email}/ban/{personId}")
    public String ban(@PathVariable Long personId,
                      @PathVariable String email) {
        System.out.println("personId " + personId + " ----username---- " + email);
        blockedService.add(personId, email);
        return "redirect:/admin";
    }

    @GetMapping("/{email}/unban/{personId}")
    public String unban(@PathVariable Long personId,
                      @PathVariable String email) {
        System.out.println("personId " + personId + " ----username---- " + email);
        blockedService.add(personId, email);
        return "redirect:/admin";
    }
}
