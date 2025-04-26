package com.springapp.first.javamodule11.service;

import com.springapp.first.javamodule11.DTO.DishGetDTO;
import com.springapp.first.javamodule11.DTO.DishPostDTO;
import com.springapp.first.javamodule11.aspect.LogExecutionTime;
import com.springapp.first.javamodule11.entity.Dish;
import com.springapp.first.javamodule11.mapper.DishMapper;
import com.springapp.first.javamodule11.repository.DishRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("DishService создан");
    }

    public List<DishGetDTO> getAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(DishMapper::toGetDTO)
                .collect(Collectors.toList());
    }

    public List<DishGetDTO> getDishesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dishRepository.findAll(pageable)
                .stream()
                .map(DishMapper::toGetDTO)
                .collect(Collectors.toList());
    }

    @LogExecutionTime
    public Page<DishGetDTO> globalSearch(String keyword, Pageable pageable) {
        Page<Dish> page = null;
        if (keyword == null || keyword.trim().isEmpty()) {
            page = dishRepository.findAll(pageable);
        }
        else {
            page = dishRepository.searchDistinctPart(keyword, pageable);
        }
        return page.map(DishMapper::toGetDTO);
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    public Dish createDish(DishPostDTO dto) {
        Dish dish = DishMapper.fromPostDto(dto);
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, DishPostDTO dto) {
        Dish existingDish = dishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Блюдо с id " + id + " не найдено"));

        existingDish.setName(dto.getName());
        existingDish.setCuisine(dto.getCuisine());

        return dishRepository.save(existingDish);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}