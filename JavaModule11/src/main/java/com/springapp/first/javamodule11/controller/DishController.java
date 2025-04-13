package com.springapp.first.javamodule11.controller;

import com.springapp.first.javamodule11.DTO.DishGetDTO;
import com.springapp.first.javamodule11.DTO.DishPostDTO;
import com.springapp.first.javamodule11.entity.Dish;
import com.springapp.first.javamodule11.mapper.DishMapper;
import com.springapp.first.javamodule11.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    //добавить необязательный query праметр отвечающий за пагинацию
    @GetMapping
    public List<DishGetDTO> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/search")
    public Page<DishGetDTO> search(@RequestParam(required = false) String keyword,
                                   @PageableDefault(page = 0, size = 1000) Pageable pageable) {
        return dishService.globalSearch(keyword, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        Optional<Dish> dish = dishService.getDishById(id);
        return dish.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public DishGetDTO createDish(@Valid @RequestBody DishPostDTO dishPostDTO) {
        Dish created = dishService.createDish(dishPostDTO);
        return DishMapper.toGetDTO(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}