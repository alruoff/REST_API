package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.entities.Technology;
import com.example.demo.entities.sets.*;
import com.example.demo.repositories.OperationARepository;
import com.example.demo.repositories.OperationBRepository;
import com.example.demo.repositories.OperationCRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MainService {

    private final OrderService orderService;
    private final TechnoService technoService;

    private final Map<String, OperationBase> operationMap;

    /**
     * Достаёт из технологии правильную последовательность Операций.
     * @param orderId - номер заказа
     * @return список Операций
     * @throws ParseException
     */
    public List<OperationBase> dispatchOrder(Long orderId) throws ParseException {

        List<OperationBase> operationList = new ArrayList<>();

        Order order = orderService.getOrderById(orderId); // заказ со всеми атрибутами доступен
        OrderBaseSet orderBaseSet = orderService.getOrderVars(orderId); // набор переменных из Заказа
        Technology techno = order.getTechno(); // привязанная к Заказу технология
        DestrictBase destrictBase = technoService.getTechnoVars(techno.getId()); // набор параметров Технологии
        // DestrictBaseSet technoBaseSet = technoService.getTechnoVars(techno.getId());

        operationList = buildOperationList(destrictBase);

        int length = operationList.size();

        for (int i = 0; i < length; i++) {

            operationList.get(i).getFace();

        }
        return null;
    }

    private List<OperationBase> buildOperationList (DestrictBase destrictBase){

        List<OperationBase> operationList = new ArrayList<>();

        int length = destrictBase.getParts().size(); //длина списка операций из технологии

        for (int i = 0; i < length; i++) {

            String s = destrictBase.getParts().get(i);
            OperationBase operation = operationMap.get(s);
            if(operation == null) operationList.add(operationMap.get("НЕРАСПОЗНАННОЕ"));
            else operationList.add(operation);

        }
        return operationList;
    }
}
