package com.restaurant.javamodule12.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDTO {
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Integer quantity;
}
