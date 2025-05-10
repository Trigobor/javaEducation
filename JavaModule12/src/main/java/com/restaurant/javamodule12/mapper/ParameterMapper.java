package com.restaurant.javamodule12.mapper;

import com.restaurant.javamodule12.DTO.RequestCategoryDTO;
import com.restaurant.javamodule12.DTO.RequestParameterDTO;
import com.restaurant.javamodule12.DTO.ResponseParameterDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Parameter;

import java.util.ArrayList;
import java.util.List;
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
}
