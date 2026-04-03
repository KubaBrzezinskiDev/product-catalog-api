package com.kubadev.technicalassessmenttask.mapper;

import com.kubadev.technicalassessmenttask.dto.ProducerDto;
import com.kubadev.technicalassessmenttask.dto.ProducerRequestDto;
import com.kubadev.technicalassessmenttask.dto.ProductDto;
import com.kubadev.technicalassessmenttask.model.Producer;
import com.kubadev.technicalassessmenttask.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProducerMapper {

    private final ProductMapper productMapper;

    public ProducerMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ProducerDto mapToDto(Producer producer) {
        ProducerDto producerDto = new ProducerDto();
        producerDto.setId(producer.getId());
        producerDto.setName(producer.getName());
        List<ProductDto> productDtoList = new ArrayList<>();
        if(producer.getProducts() != null){
            productDtoList = producer.getProducts()
                    .stream()
                    .map(productMapper::mapToDto)
                    .toList();
            producerDto.setProducts(productDtoList);
        }
        return producerDto;
    }

    public List<ProducerDto> mapToDtos(List<Producer> producers) {
        return producers
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public Producer mapToCreateProducer(ProducerRequestDto producerDto) {
        Producer producer = new Producer();
        producer.setName(producerDto.getName());
        return producer;
    }

    public void mapToUpdateProducer(Producer producer, ProducerRequestDto producerDto) {
        producer.setName(producerDto.getName());
    }
}
