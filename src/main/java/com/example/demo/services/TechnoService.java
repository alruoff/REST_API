package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.sets.*;
import com.example.demo.entities.Technology;
import com.example.demo.repositories.TechnoRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TechnoService {

    private final TechnoRepository technoRepository;

    public String createNewTechno(String name, String desc, Customer cust) {

        Technology techno = new Technology(name, desc, cust);

        technoRepository.saveAndFlush(techno);

        return "OK";
    }

    public Technology getTechnoByName(String technoName) {
        return technoRepository.getTechnologyByName(technoName);
    }

    public Technology getTechnoById(Long techId) {
        return technoRepository.getTechnologyById(techId);
    }

    public void save(Technology techno) {

        techno.setUpdated_at(LocalDateTime.now());
        technoRepository.saveAndFlush(techno);
    }

    public DestrictBase getTechnoVars(Long id) throws ParseException {

        Technology techno = technoRepository.getTechnologyById(id);

        String info = techno.getDescription(); // получили строку с параметрами данной технологии

        Gson gson = new Gson();

        return gson.fromJson(info, DestrictBase.class); // получили заполненный переменными объект к Технологии
    }

}

