package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import com.example.demo.entities.exceptiopns.LoginUniqException;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UserService userService;
    private final CustomerRepository customerRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Customer createNewCustomer(String fullName, String login, String email, String roleString) {

        if(userService.findByUsername(login).isPresent() == true) {

            try { // выбрасываем исключение
                throw new LoginUniqException("введённый логин пользователя должен быть уникальным");
            } catch (LoginUniqException e) {
                throw new RuntimeException(e);
            }

        }
        else { //только, если логин уникален

            Role role = roleService.getUserRole("ROLE_" + roleString);

            User user = new User(login, bCryptPasswordEncoder.encode(login), Arrays.asList(role));

            Customer customer = new Customer(fullName, false,  email, role.getName(), user);

            user.setCustomer(customer);

            customerRepository.saveAndFlush(customer);

            userService.loadUserByUsername(login);

            return customer;
        }

    }

    public void saveCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }
}
