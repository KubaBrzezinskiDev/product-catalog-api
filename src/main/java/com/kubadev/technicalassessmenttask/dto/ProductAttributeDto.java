package com.kubadev.technicalassessmenttask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductAttributeDto {

    private String attributeName;

    private Long attributeId;

    private String value;
}
