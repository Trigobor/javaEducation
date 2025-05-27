package com.restaurant.javamodule12.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestParameterDTO {
    @NonNull
    @NotBlank(message = "Название параметра не должно быть пустым")
    private String name;
    private String categoryName; //не всегда будет
    private String parameterType; //не всегда будет
}
