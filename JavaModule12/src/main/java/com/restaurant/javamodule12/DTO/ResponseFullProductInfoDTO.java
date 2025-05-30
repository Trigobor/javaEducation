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
public class ResponseFullProductInfoDTO {
    private String productName;
    private int quantity;
    private BigDecimal price;
    private String categoryName;
    Map<String, String> parametersToProducts;
}
