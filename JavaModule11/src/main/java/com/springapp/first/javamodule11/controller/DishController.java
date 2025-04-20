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
    public List<DishGetDTO> getAllDishes(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return dishService.getDishesPaginated(page, size);
        } else {
            return dishService.getAllDishes();
        }
    }

    //тест для pageable дожны быть параметризованными. всего тут 4 кейса, вспомни какие
    //нужно использовать аннотацию @MethodSource
    //на keyword тоже должны быть тесты
    //одлен быть один параметризованный success тест
    @GetMapping("/search")
    public Page<DishGetDTO> search(@RequestParam(required = false) String keyword,
                                   @PageableDefault(page = 0, size = 1000) Pageable pageable) {
        return dishService.globalSearch(keyword, pageable);
    }

    // что будет, еси я зайду mysite.com/dishes/idushnik
    // добавить в exceptionhandler обработку ошибок когда
    // приходят некорректные данные в тот контроллер, чтобы пользователю выдавался 400 badrequest,
    // сейчас это 500 MethodArgumentTypeMismatchException <- проверь что это
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