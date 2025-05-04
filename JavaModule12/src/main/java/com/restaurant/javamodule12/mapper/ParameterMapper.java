package com.restaurant.javamodule12.mapper;

import com.restaurant.javamodule12.DTO.RequestParameterDTO;
import com.restaurant.javamodule12.DTO.ResponseParameterDTO;
import com.restaurant.javamodule12.entity.Parameter;

import java.util.List;

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
}
