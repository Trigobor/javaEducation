package com.restaurant.javamodule12.validation;

import com.restaurant.javamodule12.DTO.RequestCreateUpdateProductParameterDTO;
import com.restaurant.javamodule12.DTO.RequestProductDTO;
import com.restaurant.javamodule12.entity.Parameter;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

        if(expectedParameters == null || expectedParameters.isEmpty()) {
            throw new BadRequestException("Вы передали парамтеры категории пустыми");
        }

        List<String> missingParams = expectedParameters.stream()
                .map(Parameter::getName)
                .filter(expectedParam -> !providedParams.containsKey(expectedParam))
                .toList();

        if(!missingParams.isEmpty()){
            throw new BadRequestException("Не переданы значения для параметров: " + String.join(", ", missingParams));
        }
    }

    public void validateProductUpdate(String productName, Integer quantity, BigDecimal price, Product product) {
        if(quantity == null && price == null && productName.equals(product.getName())) {
            throw new BadRequestException("Вы пытаетесь изменить продукт, но не передали новых данных");
        }
    }

    public void detailedValidateProductParameters(Map<String, String> providedParams, List<Parameter> expectedParameters) {
        if(providedParams == null || providedParams.isEmpty()) {
            throw new BadRequestException("Вы передали парамтеры продукта пустыми");
        }
        if(expectedParameters == null || expectedParameters.isEmpty()) {
            throw new BadRequestException("Вы передали парамтеры категории пустыми");
        }
        if (providedParams.size() != expectedParameters.size()) {
            throw new BadRequestException("Количество переданных параметров не совпадает с ожидаемым");
        }

        Set<String> expectedNames = expectedParameters.stream()
                .map(Parameter::getName)
                .collect(Collectors.toSet());

        Set<String> providedNames = providedParams.keySet();

        if (!expectedNames.equals(providedNames)) {
            StringBuilder errorMessage = new StringBuilder("Некорректный набор параметров.");
            Set<String> missing = new HashSet<>(expectedNames);
            missing.removeAll(providedNames);

            Set<String> extra = new HashSet<>(providedNames);
            extra.removeAll(expectedNames);

            if (!missing.isEmpty()) {
                errorMessage.append(" Отсутствуют параметры: ").append(String.join(", ", missing)).append(".");
            }

            if (!extra.isEmpty()) {
                errorMessage.append(" Лишние параметры: ").append(String.join(", ", extra)).append(".");
            }
            throw new BadRequestException(errorMessage.toString());
        }

    }
}
