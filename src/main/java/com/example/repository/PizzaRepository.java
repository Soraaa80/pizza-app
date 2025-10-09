package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Pizza;  //  import de la classe entity Pizza

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}