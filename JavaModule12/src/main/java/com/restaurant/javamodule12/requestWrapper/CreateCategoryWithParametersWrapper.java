package com.restaurant.javamodule12.requestWrapper;

import com.restaurant.javamodule12.DTO.RequestCategoryDTO;
import com.restaurant.javamodule12.DTO.RequestParameterDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateCategoryWithParametersWrapper {
    @Valid
    @NotNull
    private RequestCategoryDTO requestCategoryDTO;

    @Valid
    @NotNull
    private List<RequestParameterDTO> requestParameterDTOList;
}
