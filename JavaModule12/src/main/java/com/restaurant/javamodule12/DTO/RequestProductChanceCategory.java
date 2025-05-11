package com.restaurant.javamodule12.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductChanceCategory {
    @NonNull
    @NotBlank(message = "Название новой категории должно быть заполнено")
    private String newCategoryName;
    @NonNull
    @NotBlank(message = "Новые параметры продукта должны быть заполнено")
    Map<String, String> newParametersToProducts;
}
