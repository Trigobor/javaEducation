package com.restaurant.javamodule12.validation;

import com.restaurant.javamodule12.DTO.RequestCreateUpdateProductParameterDTO;
import com.restaurant.javamodule12.entity.Parameter;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductValidator {
    //TODO: hibernateValidator
    public void validateProduct(RequestCreateUpdateProductParameterDTO productDto) {
        if(productDto.getCategoryName().isEmpty() || productDto.getQuantity() == null || productDto.getPrice() == null || productDto.getParametersToProducts() == null || productDto.getParametersToProducts().isEmpty()) {
            throw new BadRequestException("Некорректные данные: проверьте категорию, количество, цену или параметры продукта");
        }
    }

    public void validateProductParameters(Map<String, String> providedParams, List<Parameter> expectedParameters) {
        if(providedParams == null || providedParams.isEmpty()) {
            throw new BadRequestException("Вы передали парамтеры продукта пустыми");
        }

        List<String> missingParams = expectedParameters.stream()
                .map(Parameter::getName)
                .filter(expectedParam -> !providedParams.containsKey(expectedParam))
                .toList();

        if(!missingParams.isEmpty()){
            throw new BadRequestException("Не переданы значения для параметров: " + String.join(", ", missingParams));
        }
    }
}
