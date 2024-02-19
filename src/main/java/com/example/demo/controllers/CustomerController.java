package com.example.demo.controllers;

import com.example.demo.controllers.models.CustomerRequestModel;
import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.services.CustomerService;
import com.example.demo.services.MainService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Поинт /new для создания нового Customer-User.
 * Создаём набор доп. атрибутов для User, связь User-Customer 1:1
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Новый Customer, расширяющий и дополняющий сущность User
     *
     * @param fullname - Задаётся дополнительное имя пользователя к логину в пару
     * @param login    - логин нового пользователя
     * @param email    - e-mail для нового пользователя
     * @param role     - задаём роль нового пользователя
     * @return
     */
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createNewCustomer(@RequestBody CustomerRequestModel requestModel) {
        log.info("Start endpoint new, response {}", requestModel);
        customerService.createNewCustomer(requestModel.getFullname(), requestModel.getLogin(), requestModel.getEmail(), requestModel.getRole());
        log.info("Stop endpoint new, request {}", requestModel);
        return ResponseEntity.ok("Ок");
    }

    /**
     * Удалить пользователя
     *
     * @param fullname - Задаётся дополнительное имя пользователя к логину в пару
     * @param login    - логин нового пользователя
     * @param email    - e-mail для нового пользователя
     * @param role     - задаём роль нового пользователя
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long userId) {
        log.info("Start endpoint delete/{}", userId);

        if (customerService.removeCustomer(userId)) {

            log.info("Stop endpoint delete/{}", userId);
            return ResponseEntity.ok("Ок");
        } else {
            log.info("There is no the Customer. Stop endpoint delete/{}", userId);
            return ResponseEntity.badRequest().body("There is no the Customer");
        }
    }

    @GetMapping(value = "/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCustomerSize() {
        log.info("Start endpoint get all Customers");
        List<Customer> listCustomer = customerService.getAllCustomers();

        return ResponseEntity.ok("{ \"amountOfCustomer\":" + listCustomer.size() + "}");

    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCustomer() {

        log.info("Start endpoint get all Customers");

        List<Customer> listCustomer = customerService.getAllCustomers();

        return ResponseEntity.ok(listCustomer.stream().map(e ->
                e.getFullName() + " -> " + e.getId()
        ).collect(Collectors.toList()).toString());


    }

}


