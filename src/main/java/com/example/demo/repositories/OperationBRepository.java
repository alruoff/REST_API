package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import com.example.demo.entities.sets.OperationA;
import com.example.demo.entities.sets.OperationB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationBRepository extends JpaRepository<OperationB, Long> {
}
