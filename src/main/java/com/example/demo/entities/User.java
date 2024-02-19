package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Login should not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9а-яА-Я_]{4,12}$", message = "Login should be correct")
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @NotBlank(message = "Password should not be blank")
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