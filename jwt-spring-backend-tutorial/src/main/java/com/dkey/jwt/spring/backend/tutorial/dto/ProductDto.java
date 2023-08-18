package com.dkey.jwt.spring.backend.tutorial.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank
    private String name;
    @Min(0)
    private Float price;
}
