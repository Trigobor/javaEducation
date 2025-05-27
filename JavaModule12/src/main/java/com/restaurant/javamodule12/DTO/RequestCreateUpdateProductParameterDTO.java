package com.restaurant.javamodule12.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//TODO: Акстись и переиспользуй DTO, не плоди хаос
//прости, я не могу, я глуп и не в состоянии противостоять хаосу
public class RequestCreateUpdateProductParameterDTO {
    @NonNull
    @NotBlank(message = "Название продукта должно быть заполнено")
    private String productName;
    @NonNull
    @NotBlank(message = "Количество продуктов должно быть заполнено")
    private Integer quantity;
    @NonNull
    @NotBlank(message = "Цена продукта должна быть заполнена")
    private BigDecimal price;
    @NonNull
    @NotBlank(message = "Название категории должно быть заполнено")
    private String categoryName;
    @NonNull
    @NotBlank(message = "Параметры продукта должны быть заполнены")
    Map<String, String> parametersToProducts;
}
