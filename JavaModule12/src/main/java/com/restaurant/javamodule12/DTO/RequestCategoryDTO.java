package com.restaurant.javamodule12.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategoryDTO {
    @NonNull
    @NotBlank(message = "Название категории блюд не должно быть пустым")
    private String categoryName;
    private List<RequestParameterDTO> parameters;
}
