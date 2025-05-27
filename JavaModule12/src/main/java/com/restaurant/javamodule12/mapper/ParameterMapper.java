package com.restaurant.javamodule12.mapper;

import com.restaurant.javamodule12.DTO.RequestCategoryDTO;
import com.restaurant.javamodule12.DTO.RequestParameterDTO;
import com.restaurant.javamodule12.DTO.ResponseParameterDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Parameter;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.entity.ProductParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParameterMapper {
    public static ResponseParameterDTO toDTO(Parameter parameter) {
        ResponseParameterDTO dto = new ResponseParameterDTO();
        dto.setName(parameter.getName());
        dto.setParameterType(parameter.getParameterType());
        return dto;
    }

    public static Parameter toEntity(RequestParameterDTO dto) {
        Parameter parameter = new Parameter();
        parameter.setName(dto.getName());
        parameter.setParameterType(dto.getParameterType());
        return parameter;
    }

    public static List<ResponseParameterDTO> toDtoList(List<Parameter> parameters) {
        return parameters.stream().map(ParameterMapper::toDTO).toList();
    }

    public static List<Parameter> toEntityList(RequestCategoryDTO categoryDto, List<RequestParameterDTO> paramsDto) {
        Category newCategory = CategoryMapper.fromRequestDto(categoryDto);
        return paramsDto.stream()
                .map(dto -> {
                    Parameter parameter = new Parameter();
                    parameter.setName(dto.getName());
                    parameter.setCategory(newCategory);
                    parameter.setParameterType(dto.getParameterType());
                    return parameter;
                })
                .toList();
    }

    public static List<Parameter> toEntityList(Category category, List<RequestParameterDTO> paramsDto) {
        return paramsDto.stream()
                .map(dto -> {
                    Parameter parameter = new Parameter();
                    parameter.setName(dto.getName());
                    parameter.setCategory(category);
                    parameter.setParameterType(dto.getParameterType());
                    return parameter;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<ProductParameter> toProductParameterEntityList(Product product, List<Parameter> categoryParameters, Map<String, String> newParametersToProducts) {
        List<ProductParameter> productParameters = new ArrayList<>();

        for (Parameter categoryParameter : categoryParameters) {
            String name = categoryParameter.getName();
            String value = newParametersToProducts.getOrDefault(name, "none");
            ProductParameter productParameter = new ProductParameter();

            productParameter.setParameter(categoryParameter);
            productParameter.setProduct(product);
            productParameter.setValue(value);

            productParameters.add(productParameter);
        }

        return productParameters;
    }
}
