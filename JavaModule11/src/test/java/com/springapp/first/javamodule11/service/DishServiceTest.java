package com.springapp.first.javamodule11.service;

import com.springapp.first.javamodule11.DTO.DishGetDTO;
import com.springapp.first.javamodule11.DTO.DishPostDTO;
import com.springapp.first.javamodule11.entity.Dish;
import com.springapp.first.javamodule11.mapper.DishMapper;
import com.springapp.first.javamodule11.repository.DishRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DishServiceTest {
    private DishRepository dishRepository;
    private DishService dishService;

    @BeforeEach
    void setUp() {
        dishRepository = mock(DishRepository.class);
        dishService = new DishService(dishRepository);
    }

    @Test
    void testGetAllDishes() {
        Dish dish = new Dish();
        dish.setId(1L);
        dish.setName("Pilaf");
        dish.setCuisine("Uzbek");

        List<Dish> dishList = List.of(dish);

        when(dishRepository.findAll()).thenReturn(dishList);

        try (MockedStatic<DishMapper> mockedMapper = Mockito.mockStatic(DishMapper.class)) {
            mockedMapper.when(() -> DishMapper.toGetDTO(dish))
                    .thenReturn(new DishGetDTO(1L, "Pilaf", "Uzbek"));

            List<DishGetDTO> result = dishService.getAllDishes();

            assertEquals(1, result.size());
            assertEquals("Pilaf", result.getFirst().getName());
        }
    }

    @Test
    void testGetDishByIdFound() {
        Dish dish = new Dish();
        dish.setId(1L);

        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));

        Optional<Dish> result = dishService.getDishById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testCreateDish() {
        DishPostDTO dto = new DishPostDTO("Bun cha", "Vietnamese");

        Dish savedDish = new Dish();
        savedDish.setName("Bun cha");
        savedDish.setCuisine("Vietnamese");

        try (MockedStatic<DishMapper> mockedMapper = Mockito.mockStatic(DishMapper.class)) {
            mockedMapper.when(() -> DishMapper.fromPostDto(dto))
                    .thenReturn(savedDish);

            when(dishRepository.save(savedDish)).thenReturn(savedDish);

            Dish result = dishService.createDish(dto);

            assertEquals("Bun cha", result.getName());
        }
    }

    @Test
    void testCreateNullDish() {
        assertThrows(NullPointerException.class, () -> {
            dishService.createDish(null);
        });
    }

    @Test
    void testUpdateDishFound() {
        Dish existingDish = new Dish();
        existingDish.setId(1L);
        existingDish.setName("Pizza");
        existingDish.setCuisine("American");

        DishPostDTO dto = new DishPostDTO("Pizza", "Italian");

        when(dishRepository.findById(1L)).thenReturn(Optional.of(existingDish));
        when(dishRepository.save(any(Dish.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Dish result = dishService.updateDish(1L, dto);

        assertEquals("Pizza", result.getName());
        assertEquals("Italian", result.getCuisine());
    }

    @Test
    void testUpdateDishNotFound() {
        when(dishRepository.findById(1L)).thenReturn(Optional.empty());

        DishPostDTO dto = new DishPostDTO("Kebab", "Iranian");

        assertThrows(EntityNotFoundException.class, () -> dishService.updateDish(1L, dto));
    }


    @Test
    void testGlobalSearchWithKeyword() {
        String keyword = "Swiss";
        PageRequest pageable = PageRequest.of(0, 10);

        Dish dish = new Dish();
        dish.setId(1L);
        List<Dish> dishList = List.of(dish);

        when(dishRepository.searchDistinctPart(eq(keyword), eq(pageable)))
                .thenReturn(new PageImpl<>(dishList));

        try (MockedStatic<DishMapper> mockedMapper = Mockito.mockStatic(DishMapper.class)) {
            mockedMapper.when(() -> DishMapper.toGetDTO(any()))
                    .thenReturn(new DishGetDTO(1L, "Swiss cheese", "American"));

            Page<DishGetDTO> result = dishService.globalSearch(keyword, pageable);

            assertEquals(1, result.getTotalElements());
        }
    }

    @Test
    void globalSearchNullKeyword() {
        Pageable pageable = PageRequest.of(0, 5);

        Dish dimSum = new Dish();
        Dish beefsteak = new Dish();

        dimSum.setId(1L);
        dimSum.setName("Dim sum");
        dimSum.setCuisine("Chinese");
        beefsteak.setId(2L);
        beefsteak.setName("Beefsteak");
        beefsteak.setCuisine("American");


        List<Dish> dishes = List.of(dimSum, beefsteak);
        Page<Dish> page = new PageImpl<>(dishes);

        when(dishRepository.findAll(pageable)).thenReturn(page);

        Page<DishGetDTO> result = dishService.globalSearch(null, pageable);

        assertEquals(2, result.getTotalElements());
        verify(dishRepository, times(1)).findAll(pageable);
        verify(dishRepository, never()).searchDistinctPart(any(), any());
    }

    @Test
    void testDeleteDish() {
        dishService.deleteDish(5L);
        verify(dishRepository, times(1)).deleteById(5L);
    }
}
