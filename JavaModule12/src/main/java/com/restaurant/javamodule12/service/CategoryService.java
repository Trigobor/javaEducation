package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.DTO.RequestCategoryDTO;
import com.restaurant.javamodule12.DTO.ResponseCategoryDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.mapper.CategoryMapper;
import com.restaurant.javamodule12.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {this.categoryRepository = categoryRepository;}

    public Category addCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    public Optional<Category> getCategoryByName(String categoryName) {
        return Optional.ofNullable(categoryRepository.findByName(categoryName));
    }

    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Page<Category> searchCategories(String keyword, Pageable pageable) {
        return categoryRepository.searchDistinctPart(keyword, pageable);
    }

    public Category updateCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

}
