package com.kubadev.technicalassessmenttask.mapper;

import com.kubadev.technicalassessmenttask.dto.ProductAttributeDto;
import com.kubadev.technicalassessmenttask.dto.ProductDto;
import com.kubadev.technicalassessmenttask.dto.ProductRequestDto;
import com.kubadev.technicalassessmenttask.model.Attribute;
import com.kubadev.technicalassessmenttask.model.Producer;
import com.kubadev.technicalassessmenttask.model.Product;
import com.kubadev.technicalassessmenttask.model.ProductAttribute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final ProductAttributeMapper productAttributeMapper;

    public ProductMapper(ProductAttributeMapper productAttributeMapper) {
        this.productAttributeMapper = productAttributeMapper;
    }

    public ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setProducerId(product.getProducer().getId());
        List<ProductAttributeDto> productAttributeDtos = new ArrayList<>();
        if(product.getProductAttributes() != null) {
            productAttributeDtos = product.getProductAttributes()
                    .stream()
                    .map(productAttributeMapper::toDto)
                    .toList();
            productDto.setProductAttributes(productAttributeDtos);
        }
        return productDto;
    }

    public List<ProductDto> mapToDtos(List<Product> products) {
        return products
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Product mapToCreateProduct(ProductRequestDto productDto, Producer producer, Map<Long, Attribute> attributes) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setProducer(producer);
        if(productDto.getProductAttributes() != null){
            for(ProductAttributeDto productAttributeDto : productDto.getProductAttributes()) {
                Attribute attribute = attributes.get(productAttributeDto.getAttributeId());
                if(attribute == null) {
                    throw new IllegalArgumentException("Invalid attribute id " + productAttributeDto.getAttributeId());
                }
                ProductAttribute productAttribute = new ProductAttribute();
                productAttribute.setAttribute(attribute);
                productAttribute.setValue(productAttributeDto.getValue());
                product.addProductAttribute(productAttribute);
            }
        }
        return product;
    }

    public void mapToUpdateProduct(Product product, ProductRequestDto productDto, Producer producer, Map<Long, Attribute> attributes) {
        product.setName(productDto.getName());
        product.setProducer(producer);
        product.getProductAttributes().clear();
        if(productDto.getProductAttributes() != null){
            for(ProductAttributeDto productAttributeDto : productDto.getProductAttributes()) {
                Attribute attribute = attributes.get(productAttributeDto.getAttributeId());
                if(attribute == null) {
                    throw new IllegalArgumentException("Invalid attribute id " + productAttributeDto.getAttributeId());
                }
                ProductAttribute productAttribute = new ProductAttribute();
                productAttribute.setAttribute(attribute);
                productAttribute.setValue(productAttributeDto.getValue());
                product.addProductAttribute(productAttribute);
            }
        }
    }
}
