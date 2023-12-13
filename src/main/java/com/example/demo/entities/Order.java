package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Класс, описывающий сущность Заказ на производстве
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "corder")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String name; // название заказа в дополнение к номеру

    @ManyToOne (cascade = CascadeType.ALL)
    private Customer customer; // ведущий менеджер заказа

    @Column(name = "variables", columnDefinition = "text")
    private String info; // набор переменных параметров и их значений для этого заказа в формате JSON

    private Boolean is_active; // заказ можно деактивировать до окончательного уточнения ТЗ, например

    @CreationTimestamp
    private LocalDateTime created_at; // дата создания Заказа
    @UpdateTimestamp
    private LocalDateTime updated_at; // дата внесения последних изменений в Заказ

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Technology techno; // технология, по которой будет обрабатываться заказ

    public Order(String name, Customer customer, String info, Boolean is_active) {

        this.name = name;
        this.customer = customer;
        this.info = info;
        this.is_active = is_active;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    public void setTechno(@NotNull Technology techno) {

        this.updated_at = LocalDateTime.now();
        this.techno = techno;
    }
}


