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

    @NotBlank(message="name of product is required")
    private String name;
    @Min(value = 0, message="price is required, and cannot be less than 0")
    private Float price;
}
