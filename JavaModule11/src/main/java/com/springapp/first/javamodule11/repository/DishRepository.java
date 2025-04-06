package com.springapp.first.javamodule11.repository;

import com.springapp.first.javamodule11.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByCuisine(String cuisine);
}
