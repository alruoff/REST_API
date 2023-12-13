package com.example.demo.repositories;

import com.example.demo.entities.sets.OperationC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationCRepository extends JpaRepository<OperationC, Long>  {
}
