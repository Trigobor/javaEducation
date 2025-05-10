package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.DTO.*;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.mapper.CategoryMapper;
import com.restaurant.javamodule12.mapper.ParameterMapper;
import com.restaurant.javamodule12.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryManagementService {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ParameterService parameterService;
    private final ProductParameterService productParameterService;

    @Autowired
    public CategoryManagementService(CategoryService categoryService, ProductService productService, ParameterService parameterService, ProductParameterService productParameterService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.parameterService = parameterService;
        this.productParameterService = productParameterService;
    }

    public ResponseCategoryDTO createCategory(RequestCategoryDTO categoryDto) {
        Category newCategory = CategoryMapper.fromRequestDto(categoryDto);
        return CategoryMapper.toDTOWithFullInfo(categoryService.addCategory(newCategory));
    }


    //модно встроить в маппер еще и добавление парамтетров
    @Transactional
    public ResponseCategoryDTO createCategoryWithParameters(RequestCategoryDTO categoryDto, List<RequestParameterDTO> paramsDto) {
        Category newCategory = CategoryMapper.fromRequestDto(categoryDto);
        return CategoryMapper.toDTOWithFullInfo(categoryService.addCategory(newCategory));
    }

    @Transactional
    public ResponseAddParametersToCategoryDTO addParametersToCategory(String categoryName, List<RequestParameterDTO> paramsDto) {
        Category category = categoryService.getCategoryByName(categoryName);
        List<Product> products = productService.getProductsBuCategoryId(category.getId());
        ResponseAddParametersToCategoryDTO addParametersToCategoryDTO = new ResponseAddParametersToCategoryDTO();

        addParametersToCategoryDTO.setCategory(CategoryMapper.toDTO(category));
        addParametersToCategoryDTO.setNewParameters(ParameterMapper.toDtoList(parameterService.addParametersList(paramsDto, category)));
        addParametersToCategoryDTO.setProducts(ProductMapper.toDTOList(products));

        return addParametersToCategoryDTO;
    }

    @Transactional
    public ResponseCategoryDTO getCategory(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        return CategoryMapper.toDTOWithFullInfo(category);
    }

    @Transactional
    public Page<ResponseCategoryDTO> searchCategories(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return categoryService.getAllCategories(pageable).map(CategoryMapper::toDTO);
        }
        else {
            return categoryService.searchCategories(keyword, pageable).map(CategoryMapper::toDTO);
        }
    }

    @Transactional
    public ResponseCategoryDTO updateCategory(String categoryName, RequestCategoryDTO categoryDto) {
        Category category = categoryService.getCategoryByName(categoryName);
        category.setName(categoryDto.getCategoryName());
        return CategoryMapper.toDTOWithFullInfo(categoryService.updateCategory(category));
    }

    @Transactional
    public ResponseCategoryDTO deleteCategory(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        ResponseCategoryDTO categoryDto = CategoryMapper.toDTOWithFullInfo(category);
        categoryService.deleteCategory(category);
        return categoryDto;
    }
}
