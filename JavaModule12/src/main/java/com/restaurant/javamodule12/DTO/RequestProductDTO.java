package com.restaurant.javamodule12.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDTO {
    @NonNull
    @NotBlank(message = "Название товара не должно быть пустым")
    private String name;
    private String category; //не всегда будет
    private BigDecimal price; //не всегда будет
    private Integer quantity; //не всегда будет
}
