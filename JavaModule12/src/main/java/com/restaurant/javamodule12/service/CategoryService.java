package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.DTO.RequestCategoryDTO;
import com.restaurant.javamodule12.DTO.ResponseCategoryDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.mapper.CategoryMapper;
import com.restaurant.javamodule12.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {this.categoryRepository = categoryRepository;}

    // TODO: сделать обработку DataIntegrityViolationException, когда будешь делать ControllerAdvice
    public Category addCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    //TODO: Сделать обработку EntityNotFoundException в ControllerAdvice
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }


}
