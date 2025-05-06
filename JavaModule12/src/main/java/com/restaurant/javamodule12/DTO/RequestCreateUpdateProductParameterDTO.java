package com.restaurant.javamodule12.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//TODO: Акстись и переиспользуй DTO, не плоди хаос
public class RequestCreateUpdateProductParameterDTO {
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private String categoryName;
    Map<String, String> parametersToProducts;
}
