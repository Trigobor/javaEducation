package com.restaurant.javamodule12.controller;

import com.restaurant.javamodule12.DTO.*;
import com.restaurant.javamodule12.requestWrapper.CreateCategoryWithParametersWrapper;
import com.restaurant.javamodule12.service.CategoryManagementService;
import com.restaurant.javamodule12.service.ProductManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class IllComeUpWithANameLaterController {
    private final ProductManagementService productManagementService;
    private final CategoryManagementService categoryManagementService;

    @Autowired
    public IllComeUpWithANameLaterController(ProductManagementService productManagementService, CategoryManagementService categoryManagementService) {
        this.productManagementService = productManagementService;
        this.categoryManagementService = categoryManagementService;
    }

    //Create
    @PostMapping("/category/add")
    public ResponseCategoryDTO createCategory(@Valid @RequestBody RequestCategoryDTO requestCategoryDTO) {
        return categoryManagementService.createCategory(requestCategoryDTO);
    }

    @PostMapping("/category/add-with-params")
    public ResponseCategoryDTO createCategoryWithParameters(@Valid @RequestBody CreateCategoryWithParametersWrapper createCategoryWithParametersWrapper) {
        return categoryManagementService.createCategoryWithParameters(createCategoryWithParametersWrapper.getRequestCategoryDTO(), createCategoryWithParametersWrapper.getRequestParameterDTOList());
    }

    //Read
    @GetMapping("category/{categoryName}")
    public ResponseCategoryDTO getCategoryFullInfo(@PathVariable String categoryName) {
        return categoryManagementService.getCategory(categoryName);
    }

    @GetMapping("category/search")
    public Page<ResponseCategoryDTO> searchCategories(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = true) Integer size,
                                                      @RequestParam(required = true) Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryManagementService.searchCategories(keyword, pageable);
    }

    //Update
    @PostMapping("/category/add-parameters")
    public ResponseAddParametersToCategoryDTO addParametersToCategory(@Valid @RequestBody CreateCategoryWithParametersWrapper createCategoryWithParametersWrapper) {
        return categoryManagementService.addParametersToCategory(createCategoryWithParametersWrapper.getRequestCategoryDTO().getCategoryName(), createCategoryWithParametersWrapper.getRequestParameterDTOList());
    }

    @PostMapping("/category/{categoryName}/update")
    public ResponseCategoryDTO updateCategory(@Valid @RequestBody RequestCategoryDTO requestCategoryDTO, @PathVariable String categoryName) {
        return categoryManagementService.updateCategory(categoryName, requestCategoryDTO);
    }

    //Delete
    @PostMapping("category/delete")
    public ResponseCategoryDTO deleteCategory(@Valid @RequestBody RequestCategoryDTO requestCategoryDTO) {
        return categoryManagementService.deleteCategory(requestCategoryDTO.getCategoryName());
    }

    @PostMapping("product/add")
    public ResponseProductDTO addProduct(@Valid @RequestBody RequestCreateUpdateProductParameterDTO requestCreateProductDTO) {
        return productManagementService.addProduct(requestCreateProductDTO);
    }

    //getProduct

    //searchProduct

    //changeCategoryOfProduct

    //updateProduct

    //deleteProduct
}
