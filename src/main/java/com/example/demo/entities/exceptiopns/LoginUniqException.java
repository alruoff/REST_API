package com.example.demo.entities.exceptiopns;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class LoginUniqException extends Exception {

    public LoginUniqException(String message) {
        super(message);
        System.out.println("ОШИБКА: " + message);
    }
}
