package com.springapp.first.javamodule11.controller;

import com.springapp.first.javamodule11.DTO.DishGetDTO;
import com.springapp.first.javamodule11.DTO.DishPostDTO;
import com.springapp.first.javamodule11.entity.Dish;
import com.springapp.first.javamodule11.mapper.DishMapper;
import com.springapp.first.javamodule11.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public List<DishGetDTO> getAllDishes(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return dishService.getDishesPaginated(page, size);
        } else {
            return dishService.getAllDishes();
        }
    }

    @GetMapping("/search")
    public Page<DishGetDTO> search(@RequestParam(required = false) String keyword,
                                   @RequestParam(required = true) Integer size,
                                   @RequestParam(required = true) Integer page){

        Pageable pageable = PageRequest.of(page, size);
        return dishService.globalSearch(keyword, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID не может быть отрицательным");
        }
        Optional<Dish> dish = dishService.getDishById(id);
        return dish.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public DishGetDTO createDish(@Valid @RequestBody DishPostDTO dishPostDTO) {
        Dish created = dishService.createDish(dishPostDTO);
        return DishMapper.toGetDTO(created);
    }

    @PutMapping("/{id}")
    public DishGetDTO updateDish(@PathVariable Long id, @Valid @RequestBody DishPostDTO dishPostDTO) {
        Dish updated = dishService.updateDish(id, dishPostDTO);
        return DishMapper.toGetDTO(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}