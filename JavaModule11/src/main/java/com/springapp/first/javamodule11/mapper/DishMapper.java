package com.springapp.first.javamodule11.mapper;

import com.springapp.first.javamodule11.DTO.DishGetDTO;
import com.springapp.first.javamodule11.DTO.DishPostDTO;
import com.springapp.first.javamodule11.entity.Dish;

public class DishMapper {

    public static DishPostDTO toPostDTO(Dish dish) {
        return new DishPostDTO(dish.getName(), dish.getCuisine());
    }

    public static DishGetDTO toGetDTO(Dish dish) {
        return new DishGetDTO(dish.getId(), dish.getName(), dish.getCuisine());
    }

    public static Dish fromPostDto(DishPostDTO dto) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setCuisine(dto.getCuisine());
        return dish;
    }

    public static Dish toEntity(DishGetDTO dto) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setCuisine(dto.getCuisine());
        return dish;
    }
}
