package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывающий сущность Поьльзователь системы
 */
@Data
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Fullname should be between 4 and 12 letters")
    @Size(min =4 , max = 48)
    private String fullName; // иное, чем логин. Отдельное доп. имя в системе

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    private Boolean is_blocked;

    @Email
    private String email;
    private String position; // роль сотрудника в системе

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private User user;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Order> listOfCorders;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "techno_id")
    private List<Technology> listOfTechno;

    public Customer(String name, Boolean is_blocked, String email, String position, User user) {
        this.fullName = name;
        this.is_blocked = is_blocked;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.email = email;
        this.position = position;
        this.user = user;
        this.listOfCorders = new ArrayList<>();
        this.listOfTechno = new ArrayList<>();
    }
    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", is_blocked=" + is_blocked +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                // ", user=" + user +
                '}';
    }
}