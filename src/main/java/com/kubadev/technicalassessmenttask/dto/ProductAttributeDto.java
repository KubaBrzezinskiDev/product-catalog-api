package com.kubadev.technicalassessmenttask.dto;

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
