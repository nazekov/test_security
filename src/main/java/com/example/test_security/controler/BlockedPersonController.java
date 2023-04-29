package com.example.test_security.controler;

import com.example.test_security.service.BlockedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/black-list")
public class BlockedPersonController {

    private final BlockedService blockedService;

    public BlockedPersonController(BlockedService blockedService) {
        this.blockedService = blockedService;
    }
}
