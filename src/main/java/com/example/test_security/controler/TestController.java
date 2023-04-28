package com.example.test_security.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/all")
    public String getAll() {
        return "Hello Everybody. Hello Admin. Hello Moderator. Hello User. Hello Guest.";
    }

    @GetMapping("/three")
    public String getThree() {
        return "Hello Admin. Hello Moderator. Hello User.";
    }

    @GetMapping("/two")
    public String getTwo() {
        return "Hello Admin. Hello Moderator.";
    }

    @GetMapping("/one")
    public String getOne() {
        return "Hello Admin.";
    }
}
