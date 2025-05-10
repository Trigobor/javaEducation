package com.restaurant.javamodule12.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddParametersToCategoryDTO {
    ResponseCategoryDTO category;
    List<ResponseParameterDTO> newParameters;
    List<ResponseProductDTO> products;
}
