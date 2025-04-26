package com.springapp.first.javamodule11.repository;

import com.springapp.first.javamodule11.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByCuisine(String cuisine);

    //по задумке можно сделать два блюда омлет пуляр и омлет тамаго-яки, метод вернет оба
    @Query("SELECT d FROM Dish d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    List<Dish> searchByDishNamePart(@Param("namePart") String dishNamePart);

    //то же самое, но с кухней, правда оба метода нигде не используются пока
    @Query("SELECT d FROM Dish d WHERE LOWER(d.cuisine) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    List<Dish> searchByCuisineNamePart(@Param("namePart") String cuisineNamePart);

    @Query("SELECT DISTINCT d FROM Dish d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :namePart, '%')) OR LOWER(d.cuisine) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    Page<Dish> searchDistinctPart(@Param("namePart") String namePart, Pageable pageable);
}
