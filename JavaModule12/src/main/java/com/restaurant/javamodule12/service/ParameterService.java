package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.DTO.RequestParameterDTO;
import com.restaurant.javamodule12.DTO.ResponseParameterDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Parameter;
import com.restaurant.javamodule12.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParameterService {

    @Autowired
    private final ParameterRepository parameterRepository;

    @Autowired
    public ParameterService(ParameterRepository parameterRepository) {this.parameterRepository = parameterRepository;}

    public List<Parameter> addParametersList(List<RequestParameterDTO> paramsDto, Category category){
        List<Parameter> params = paramsDto.stream()
                .map(dto -> {
                    Parameter parameter = new Parameter();
                    parameter.setName(dto.getName());
                    parameter.setCategory(category);
                    parameter.setParameterType(dto.getParameterType());
                    return parameter;
                })
                .toList();
        return parameterRepository.saveAll(params);
    }
}
