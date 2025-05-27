package com.restaurant.javamodule12.mapper;

import com.restaurant.javamodule12.DTO.RequestCategoryDTO;
import com.restaurant.javamodule12.DTO.ResponseCategoryDTO;
import com.restaurant.javamodule12.DTO.ResponseParameterDTO;
import com.restaurant.javamodule12.DTO.ResponseProductDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Parameter;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static Category fromRequestDto(RequestCategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getCategoryName());

        if (dto.getParameters() != null) {
            List<Parameter> parameters = dto.getParameters().stream()
                    .map(paramDto -> {
                        Parameter param = ParameterMapper.toEntity(paramDto);
                        param.setCategory(category);
                        return param;
                    })
                    .collect(Collectors.toList());
            category.setParameters(parameters);
        }

        return category;
    }

    public static ResponseCategoryDTO toDTOWithFullInfo(Category category) {
        List<ResponseParameterDTO> parameterDTOs = null;
        if (category.getParameters() != null) {
            parameterDTOs = category.getParameters().stream()
                    .map(ParameterMapper::toDTO)
                    .collect(Collectors.toList());
        }

        List<ResponseProductDTO> productDTOs = null;
        if (category.getProducts() != null) {
            productDTOs = category.getProducts().stream()
                    .map(ProductMapper::toDTO)
                    .collect(Collectors.toList());
        }

        return new ResponseCategoryDTO(
                category.getName(),
                parameterDTOs,
                productDTOs
        );
    }


    public static ResponseCategoryDTO toDTO(Category category) {
        return new ResponseCategoryDTO(
                category.getName(), null, null
        );
    }
}
