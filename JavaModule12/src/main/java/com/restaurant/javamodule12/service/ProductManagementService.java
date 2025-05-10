package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.DTO.*;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Parameter;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.mapper.ParameterMapper;
import com.restaurant.javamodule12.mapper.CategoryMapper;
import com.restaurant.javamodule12.mapper.ProductMapper;
import com.restaurant.javamodule12.validation.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//TODO: насчет addProduct и createParametersForCategory поняьно, они работают с несколькими репозиториями, но вот оставшиеся методы, можно ли унести?
// кабута можно сделать лучше и разнести функционал этого сервиса по другим сервисам
//TODO: подумать хорошечно над тем, что по-факту этот сервис сейчас занимается 3 вещами сразу:
// диррежирует другими сервисами, фигачит свою логику работы и перепаковывает данные
@Service
public class ProductManagementService {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ParameterService parameterService;
    private final ProductParameterService productParameterService;

    private final ProductValidator productValidator;

    @Autowired
    public ProductManagementService(CategoryService categoryService, ProductService productService, ParameterService parameterService, ProductParameterService productParameterService, ProductValidator productValidator) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.parameterService = parameterService;
        this.productParameterService = productParameterService;
        this.productValidator = productValidator;
    }

    //TODO: обработать свой кастмоный BadRequestException в controllerAdvice
    @Transactional
    public ResponseProductDTO addProduct(RequestCreateUpdateProductParameterDTO productDto) {
        productValidator.validateProduct(productDto);

        Category category = categoryService.getCategoryByName(productDto.getCategoryName());
        Map<String, String> providedParams = productDto.getParametersToProducts();
        List<Parameter> expectedParams = category.getParameters();

        //TODO: неполохо было бы вставить проверки на лишние параметры и на соответвие типов параметров, но я не знаю как это пока осуществить
        productValidator.validateProductParameters(providedParams, expectedParams);

        //TODO: хорошечно подумать могу ли я НЕ перебирать expectedParams два раза
        Product savingProduct = ProductMapper.toEntity(productDto, category, expectedParams);

        return ProductMapper.toDTO(productService.addProduct(savingProduct));
    }
}
