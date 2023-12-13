package com.example.demo.repositories;

import com.example.demo.entities.Order;
import com.example.demo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByCustomer(Customer customer );
    Order getOrderById(Long orderId);
}
