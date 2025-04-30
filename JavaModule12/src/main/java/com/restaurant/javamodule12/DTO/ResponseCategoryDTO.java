package com.restaurant.javamodule12.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCategoryDTO {
    @NonNull
    private String categoryName;
    private List<ResponseParameterDTO> parameters; // не всегда будет
    private List<ResponseProductDTO> products; // не всегда будет
}
