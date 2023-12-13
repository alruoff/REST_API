package com.example.demo.entities.sets;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class OperationC implements OperationBase {

    @Id
    @GeneratedValue
    private Long id;
    private  String  name; // имя операции
    private LocalDateTime datum; // дата когда заказ выполняется
    private String sizeString; // формат работы
    private Integer amountOfOperation; // кол-во операций
    private Integer addAmount; // дополнительно для послед. операций

    private String info; // прочая информация от технолога, плановая скорость или т.п.

    public OperationC(String name) {
        this.name = name;
    }

    @Override
    public void getFace () {
        System.out.println( "Call OperationC::getFace()");
    };
}
