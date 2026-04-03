package com.kubadev.technicalassessmenttask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private Long producerId;

    @NotBlank
    private String name;

    private List<ProductAttributeDto> productAttributes;

}
