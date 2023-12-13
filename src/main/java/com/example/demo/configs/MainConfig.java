package com.example.demo.configs;

import com.example.demo.entities.sets.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class MainConfig {

    private Map<String, OperationBase> operationMap;
    // private List<OperationBase> operationList;

    public MainConfig() {

        this.operationMap = new HashMap<>();
        // this.operationList = new ArrayList<>();

        OperationBase o1 = new OperationA("Участок 1"); // различные Участки и/или Операции на производстве
        OperationBase o2 = new OperationB("Участок 2");
        OperationBase o3 = new OperationC("Участок 3");
        OperationBase o4 = new OperationD("Участок 4");
        OperationBase o5 = new OperationE("Участок 5");
        OperationBase o6 = new OperationE("Участок контроля"); // тут окажутся все неопознанные операции


        operationMap.put("Склад Бумаги", o1); // соответствие "названия участка" и операции
        operationMap.put("СБМ", o1);
        operationMap.put("Резка бумаги в печать", o2);
        operationMap.put("Печать CMYK", o3);
        operationMap.put("Печать А2 4/0", o3);
        operationMap.put("Печать А2 4+0", o3);
        operationMap.put("Резка продукции", o2);
        operationMap.put("Упаковка", o4);
        operationMap.put("Склад Готовой Продукции", o5);
        operationMap.put("СГП", o5);
        operationMap.put("НЕРАСПОЗНАННОЕ", o6);

    }
    @Bean
    public Map<String, OperationBase> getOperationMap(){ return operationMap; }
}
