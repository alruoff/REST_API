package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.entities.Customer;
import com.example.demo.entities.sets.OrderBaseSet;
import com.example.demo.entities.sets.VarOfBrochureSetOrder;
import com.example.demo.entities.sets.VarOfPlainSetOrder;
import com.example.demo.repositories.OrderRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TechnoService technoService;

    public Order createNewOrder(Customer customer, String name, String info) {

        Order corder = new Order(name, customer, info, true);

        customer.getListOfCorders().add(corder); // новый заказ подцепился в список заказов

        // customerRepository.saveAndFlush(customer);

        orderRepository.saveAndFlush(corder);

        return corder;
    }

    public List<Order> getAllCOrders(Customer customer) {

        return orderRepository.findAllByCustomer(customer);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public void saveOrder(Order corder) {
        orderRepository.saveAndFlush(corder);
    }

    public String getOrderType(Long id) throws ParseException {

        Order order = orderRepository.getOrderById(id);

        if (order == null) return "ERROR - wrong Order id";

        JSONParser jp = new JSONParser();

        JSONObject root = (JSONObject) jp.parse(order.getInfo());

        return (String) root.get("type");
    }

    public OrderBaseSet getOrderVars(Long id) throws ParseException {

        Order order = orderRepository.getOrderById(id);

        Gson gson = new Gson();

        OrderBaseSet set;

        if(getOrderType(id).equalsIgnoreCase("Брошюра")) {
            set = new VarOfBrochureSetOrder();
        }
        else  if(getOrderType(id).equalsIgnoreCase("Листовка")) {
            set = new VarOfPlainSetOrder();
        }
        else return null;

        return gson.fromJson(order.getInfo(), set.getClass());
    }


}
