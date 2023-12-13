package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.entities.sets.DestrictBase;
import com.example.demo.entities.Technology;
import com.example.demo.entities.User;
import com.example.demo.services.OrderService;
import com.example.demo.services.TechnoService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * Работает с сущностью Технология, доступ только Технолога (ROLE_TECHNOMAN)
 */
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_TECHNOMAN')") // доступно для технолога
@RequestMapping("/technology")
public class TechnologyController {

    private final TechnoService technoService;
    private final UserService userService;
    private final OrderService orderService;

    /**
     * Создаёт новую технологию
     * @param technoName Имя создаваемой технологии
     * @param description Описание в виде JSON
     * @param principal текщий пользователь
     * @return в виде строки сообщение
     */
    @PostMapping("/create/{technoName}")
    public String createTechnology(@PathVariable String technoName,
                                      @RequestParam(name = "Description") String description,
                                      Principal principal)
    {
        Optional<User> userOpt = (Optional<User>) userService.findByUsername(principal.getName());

        if(userOpt.isPresent()) {
            technoService.createNewTechno(technoName, description, userOpt.get().getCustomer());
            return String.format("OK - New technology %s was created.", technoName );
        } else return String.format("ERROR - New technology %s was not created.", technoName );

    }

    // позволяеит временно деактивировать технологию
    @PutMapping("/setoff/{techId}")

    public String setTechnologyOff(@PathVariable Long techId)
    {
        Technology techno = technoService.getTechnoById( techId );

        if (techno != null) {

            techno.setIs_active(false);

            technoService.save(techno);

            return String.format("Technology %s is OFF now.", techno.getName());
        }
        else return String.format("Technology %d is not exist", techno.getId());
    }

    // позволяеит активировать технологию, после деактивации
    @PutMapping("/seton/{techId}")

    public String setTechnologyOn(@PathVariable Long techId)
    {
        Technology techno = technoService.getTechnoById( techId );

        if (techno != null) {

            techno.setIs_active(true);

            technoService.save(techno);

            return String.format("Technology %s is ON now.", techno.getName());
        }
        else return String.format("Technology %d is not exist", techno.getId());
    }

    /**
     * Устанавливает взаимосвязь между Заказом и Технологией (n:1)
     * @param techId - идент. Технологии
     * @param orderId - идент. Заказа
     * @param principal - тек. пользователь
     * @return
     */
    @PutMapping("/set")
    public String setTechnology(@RequestParam(name = "TechID") Long techId,
                                @RequestParam(name = "OrderID") Long orderId,
                                Principal principal) {

        Order order = orderService.getOrderById(orderId);
        Technology techno = technoService.getTechnoById(techId);

        if ( techno != null && techno.getIs_active() && order != null ) {
            order.setTechno(techno);
            techno.addOrderToOrdersList(order);
            orderService.saveOrder(order);
            return String.format("OK - Technology %d::%s for the order %d::%s was set.", techno.getId(), techno.getName(), order.getId(), order.getName());
        }
        else return String.format("Technology was not set.", techno.getName());
    }

    @DeleteMapping("/delete/{techId}")

    public String setTechnology(@PathVariable Long techId) {

        Technology techno = technoService.getTechnoById(techId);

        if ( !techno.getIs_active() && techno != null ) {

            techno.clearOrdersList();
            techno.setOff();
            technoService.save(techno);
            return String.format("OK - Technology %d::%s was delete.", techno.getId(), techno.getName());
        }
        else return String.format("Technology %s is still active or not exist.", techno.getName());
    }

    /**
     * Возвращает объект с набором переменных для заданной технологии.
     * @param techId
     * @return
     * @throws ParseException - не получилось распознать набор параметров
     */
    @GetMapping("/vars/{techId}")

    public DestrictBase getInfo(@PathVariable Long techId) throws ParseException {

        return technoService.getTechnoVars(techId);
    }


}