package com.example.demo.entities.sets;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;

@Data
public class DestrictBase { // класс для всех УЧАСТКОВ на производстве

    public String name; // название технологии

    private LinkedList<String> parts; // массив с названиями участков
}
