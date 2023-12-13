package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService ;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final static String NOT_STARTED = "The system was not started";

    private final UserService userService;

    /**
     * Позволяет посмотреть текущего пользователя
     * @param principal
     * @return
     */
    @GetMapping("/view")
    public ResponseEntity<String> viewUsers(Principal principal) {

        if (principal == null) return ResponseEntity.badRequest().body(NOT_STARTED);
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return ResponseEntity.ok("Authenticated: " + user.getLogin() + " as " + user.getCustomer().getPosition());
    }
}
