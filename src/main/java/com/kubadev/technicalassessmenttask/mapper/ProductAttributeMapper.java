package com.kubadev.technicalassessmenttask.mapper;

import com.kubadev.technicalassessmenttask.dto.ProductAttributeDto;
import com.kubadev.technicalassessmenttask.model.ProductAttribute;
import org.springframework.stereotype.Component;

@Component
public class ProductAttributeMapper {

    public ProductAttributeDto toDto(ProductAttribute productAttribute) {
        ProductAttributeDto productAttributeDto = new ProductAttributeDto();
        productAttributeDto.setAttributeName(productAttribute.getAttribute().getName());
        productAttributeDto.setAttributeId(productAttribute.getAttribute().getId());
        productAttributeDto.setValue(productAttribute.getValue());
        return productAttributeDto;
    }
}
