package com.springapp.first.javamodule11.DTO;

import jakarta.validation.constraints.NotBlank;

public class DishPostDTO {
    @NotBlank(message = "Название блюда не должно быть пустым")
    private String name;

    @NotBlank(message = "Тип кухни не должен быть пустым")
    private String cuisine;

    public DishPostDTO(String name, String cuisine) {
        this.name = name;
        this.cuisine = cuisine;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
