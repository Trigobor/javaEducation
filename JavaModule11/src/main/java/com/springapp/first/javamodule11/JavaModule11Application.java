package com.springapp.first.javamodule11;

import com.springapp.first.javamodule11.entity.Dish;
import com.springapp.first.javamodule11.mapper.DishMapper;
import com.springapp.first.javamodule11.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaModule11Application implements CommandLineRunner {

    @Autowired
    private DishService dishService;

    public static void main(String[] args) {
        SpringApplication.run(JavaModule11Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Dish ramen = new Dish();
        ramen.setName("Sushi");
        ramen.setCuisine("Japanese");

        dishService.createDish(DishMapper.toPostDTO(ramen));
    }

}
