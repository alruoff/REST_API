package com.example.demo.controllers;
/**
 *  поинты для работы с сущностью "Заказ"
 */

import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.entities.sets.OperationBase;
import com.example.demo.entities.sets.OrderBaseSet;
import com.example.demo.services.MainService;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_MANAGER')")
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final MainService mainService;

    /**
     * Создаём новый Заказ
     * @param orderName - имя Заказа
     * @param info - строка с описанием переменных заказа
     * @param principal - имя тек. пользователя
     * @return
     */
    @PostMapping(value = "/new")
    public ResponseEntity<String> createNewOrder(@RequestParam(name = "name") String orderName,
                                         @RequestParam(name = "info") String info, Principal principal) {

        User user = getUser(principal);

        orderService.createNewOrder(user.getCustomer(), orderName, info);

        return new ResponseEntity<>("Новый заказ успешно создан", HttpStatus.OK);
    }

    /**
     * Список заказов текущего пользователя
     * @param principal
     * @return список заказов у текущего пользователя
     */
    @GetMapping(value ="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllOrders(Principal principal) {

        User user = getUser(principal);

        Customer cust = user.getCustomer();

        return orderService.getAllCOrders(cust).stream().map(e -> {
            return "Order # " + e.getId() + " : " + e.getName();
        }).collect(Collectors.toList());
    }

    /**
     * Возвращает сущность User текущего пользователя
     * @param principal
     * @return
     */
    private User getUser(Principal principal) {

        return userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
    }

    /**
     * Получаем тип Заказа по его id
     * @param orderId
     * @return
     * @throws ParseException
     */
    @GetMapping("/type/{orderId}")
    public String getOrderType(@PathVariable Long orderId) throws ParseException {

        return orderService.getOrderType(orderId);
    }

    /**
     * Получаем Набор переменных по id Заказа. Этот набор зависит от типа заказа
     * Для разных типов Заказов получаем разные наборы
     * @param orderId
     * @return Набор переменных заказа
     * @throws ParseException
     */
    @GetMapping("/vars/{orderId}")
    public OrderBaseSet getOrderVars(@PathVariable Long orderId) throws ParseException {

        return orderService.getOrderVars(orderId);
    }
    @GetMapping("/dispatch/{orderId}")

    public List<OperationBase> getInfo(@PathVariable Long orderId) throws ParseException {

        return mainService.dispatchOrder(orderId);
    }
}
