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
public class OperationE implements OperationBase {

    @Id
    @GeneratedValue
    private Long id;
    private  String  name; // имя операции
    private LocalDateTime planDate; // дата когда заказ запланирован

    private String sizeString; // формат работы
    private Integer amountOfOperation; // кол-во операций
    private Integer addAmount; // дополнительно для послед. операций

    private Double planTime; // запланированная длительность операции в часах
    private Double factTime; // фактическая длительность операции в часах

    public OperationE(String name) {
        this.name = name;
    }
    @Override
    public void getFace () {
        System.out.println( "Call OperationE::getFace()");
    };
}
