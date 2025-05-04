package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.DTO.*;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Parameter;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.entity.ProductParameter;
import com.restaurant.javamodule12.mapper.ParameterMapper;
import com.restaurant.javamodule12.mapper.CategoryMapper;
import com.restaurant.javamodule12.exception.BadRequestException;
import com.restaurant.javamodule12.mapper.ProductMapper;
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

    @Autowired
    public ProductManagementService(CategoryService categoryService, ProductService productService, ParameterService parameterService, ProductParameterService productParameterService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.parameterService = parameterService;
        this.productParameterService = productParameterService;
    }

    public ResponseCategoryDTO addCategory(RequestCategoryDTO categoryDto) {
        Category newCategory = CategoryMapper.fromRequestDto(categoryDto);
        return CategoryMapper.toDTO(categoryService.addCategory(newCategory));
    }

    public ResponseCategoryDTO createCategoryWithParameters(RequestCategoryDTO categoryDto, List<RequestParameterDTO> paramsDto) {
        Category newCategory = CategoryMapper.fromRequestDto(categoryDto);
        List<Parameter> newParameters = paramsDto.stream()
                .map(dto -> {
                    Parameter parameter = new Parameter();
                    parameter.setName(dto.getName());
                    parameter.setCategory(newCategory);
                    parameter.setParameterType(dto.getParameterType());
                    return parameter;
                })
                .toList();
        newCategory.setParameters(newParameters);
        return CategoryMapper.toDTO(categoryService.addCategory(newCategory));
    }

    @Transactional
    public List<ResponseParameterDTO> createParametersForCategory(List<RequestParameterDTO> paramsDto, RequestCategoryDTO categoryDto) {
        String categoryName = categoryDto.getCategoryName();
        Category category = categoryService.getCategoryByName(categoryName);
        return ParameterMapper.toDtoList(parameterService.addParametersList(paramsDto, category));
    }

    //TODO: обработать свой кастмоный BadRequestException в controllerAdvice
    @Transactional
    public ResponseProductDTO addProduct(RequestCreateUpdateProductParameterDTO productDto) {
        if(productDto.getCategoryName().isEmpty() || productDto.getQuantity() == null || productDto.getPrice() == null){
            throw new BadRequestException("Некорректные данные: проверьте категорию, количество или цену продукта");
        }

        String categoryName = productDto.getCategoryName();
        Category category = categoryService.getCategoryByName(categoryName);
        List<Parameter> expectedParams = category.getParameters();
        Map<String, String> providedParams = productDto.getParametersToProducts();

        List<String> missingParams = expectedParams.stream()
                .map(Parameter::getName)
                .filter(expectedParam -> !providedParams.containsKey(expectedParam))
                .toList();

        if(!missingParams.isEmpty()){
            throw new BadRequestException("Не переданы значения для параметров: " + String.join(", ", missingParams));
        }

        //TODO: неполохо было бы вставить проверки на лишние параметры и на соответвие типов параметров, но я не знаю как это пока осуществить
        //TODO: хорошечно подумать могу ли я НЕ перебирать expectedParams два раза

        Product savingProduct = new Product();
        savingProduct.setName(productDto.getProductName());
        savingProduct.setCategory(category);
        savingProduct.setPrice(productDto.getPrice());
        savingProduct.setQuantity(productDto.getQuantity());

        List<ProductParameter> savingParams = expectedParams.stream()
                .map(Parameter -> {
                    ProductParameter productParam = new ProductParameter();
                    productParam.setProduct(savingProduct);
                    productParam.setParameter(Parameter);
                    productParam.setValue(providedParams.get(Parameter.getName()));
                    return productParam;
                })
                .toList();

        savingProduct.setProductParameters(savingParams);
        return ProductMapper.toDTO(productService.addProduct(savingProduct));

    }

}
