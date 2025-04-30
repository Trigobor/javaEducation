package com.restaurant.javamodule12.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseParameterDTO {
    @NonNull
    private String name;
    @NonNull
    private String parameterType;
}
