package com.example.demo.entities.exceptiopns;

public class JsonProcessingException extends Exception {

    public JsonProcessingException(String message) {
        super(message);
        System.out.println("ОШИБКА: " + message);
    }
}
