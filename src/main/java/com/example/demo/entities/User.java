package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min =4 , max = 12)
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @NotEmpty(message = "Password should not be empty")
    @Size(min =4 , max = 12)
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToOne(cascade = CascadeType.ALL) // mappedBy = "user", поле user в таблице Customer
    @JoinColumn(name="customer_id")
    private Customer customer;

    public User(String login, String password, Collection<Role> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", customer=" + customer +
                '}';
    }
}