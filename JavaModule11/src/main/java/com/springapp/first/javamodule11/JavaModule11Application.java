package com.springapp.first.javamodule11;


import com.springapp.first.javamodule11.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaModule11Application {

    @Autowired
    private DishService dishService;

    public static void main(String[] args) {
        SpringApplication.run(JavaModule11Application.class, args);
    }

}
