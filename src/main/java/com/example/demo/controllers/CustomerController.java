package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.services.CustomerService;
import com.example.demo.services.MainService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * Поинт /new для создания нового Customer-User.
 * Создаём набор доп. атрибутов для User, связь User-Customer 1:1
 */
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Новый Customer, расширяющий сущность User
     * @param fullname - Задаётся дополнительное имя пользователя к логину в пару
     * @param login - логин нового пользователя
     * @param email - e-mail для нового пользователя
     * @param role - задаём роль нового пользователя
     * @return
     */
    @PostMapping ("/new")
    public String createNewCustomer(  @RequestParam(name="fullname") String fullname,
                                        @RequestParam(name="login") String login,
                                        @RequestParam(name="email") String email,
                                        @RequestParam(name="role") String role ) {

        customerService.createNewCustomer(fullname, login ,email, role);

        return "OK";
    }

}
