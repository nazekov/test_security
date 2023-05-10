package com.example.test_security.controler;

import com.example.test_security.model.BlockedRequisite;
import com.example.test_security.service.BlockedRequisiteService;
import com.example.test_security.util.ValidateRequisiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/black-list")
public class BlockedRequisiteController {

    private final BlockedRequisiteService blockedRequisiteService;

    private final ValidateRequisiteService validateRequisiteService;

    public BlockedRequisiteController(BlockedRequisiteService blockedRequisiteService,
                                      ValidateRequisiteService validateRequisiteService) {
        this.blockedRequisiteService = blockedRequisiteService;
        this.validateRequisiteService = validateRequisiteService;
    }

    @GetMapping
    public String getBanForm(Model model) {
        model.addAttribute("blockedRequisite", new BlockedRequisite());
        model.addAttribute("blockedRequisiteList",
                                            blockedRequisiteService.findAll());
        return "admin-dir/ban-panel";
    }

    @PostMapping("/{username}/ban")
    public String ban(@ModelAttribute BlockedRequisite blockedRequisite,
                        @PathVariable String username) {
        System.out.println("username: " + username);
        System.out.println("BlockedRequisite: " + blockedRequisite);

        if (validateRequisiteService.findAlreadyBannedRequisite(blockedRequisite).isPresent()) {
            return "errors/ban-error";
        }
        blockedRequisiteService.ban(blockedRequisite, username);
        return "redirect:/black-list";
    }

    @GetMapping("/{username}/ban/{requisiteId}")
    public String ban(@PathVariable String username,
                      @PathVariable Long requisiteId) {
        blockedRequisiteService.update(requisiteId, username);
        return "redirect:/black-list";
    }

    @GetMapping("/{username}/unban/{requisiteId}")
    public String unban(@PathVariable String username,
                        @PathVariable Long requisiteId) {
        System.out.println("requisiteId " + requisiteId + " ----username---- " + username);
        blockedRequisiteService.update(requisiteId, username);
        return "redirect:/black-list";
    }
}
