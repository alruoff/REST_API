package com.example.demo.controllers;

import com.example.demo.services.MainService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

/**
 * Для Spring security корневой общедоступный поинт
 */
@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final MainService mainService;
    @GetMapping("/")
    public String homePage(Principal principal) {

        //mainService.initial();
        return "Номе - current login is: " + principal.getName();
    }

    @PutMapping("/initial")
    public String initial() {

        // mainService.initial();
        return "Initial status OK";
    }
}
