package com.example.demo.controllers.models;

import lombok.Data;

/**
 * Информация по пользователю системы
 */
@Data
public class CustomerRequestModel {

    private String fullname;
    private String login;
    private String email;
    private String role;

}
