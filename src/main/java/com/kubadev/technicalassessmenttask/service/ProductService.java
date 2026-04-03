package com.kubadev.technicalassessmenttask.service;

import com.kubadev.technicalassessmenttask.dto.ProductAttributeDto;
import com.kubadev.technicalassessmenttask.dto.ProductDto;
import com.kubadev.technicalassessmenttask.dto.ProductRequestDto;
import com.kubadev.technicalassessmenttask.mapper.ProductMapper;
import com.kubadev.technicalassessmenttask.model.Attribute;
import com.kubadev.technicalassessmenttask.model.Producer;
import com.kubadev.technicalassessmenttask.model.Product;
import com.kubadev.technicalassessmenttask.repository.AttributeRepository;
import com.kubadev.technicalassessmenttask.repository.ProducerRepository;
import com.kubadev.technicalassessmenttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;
    private final AttributeRepository attributeRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          ProducerRepository producerRepository,
                          AttributeRepository attributeRepository
    ) {
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
        this.attributeRepository = attributeRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.mapToDtos(products);
    }

    public List<ProductDto> getAllFilteredProducts(List<Long> attributeIds) {
        List<Product> products = productRepository.findByAttributes(attributeIds, attributeIds.size());
        return  productMapper.mapToDtos(products);
    }

    public List<ProductDto> getAllSearchedProducts(String query) {
        List<Product> products = productRepository.searchByParameter(query);
        return productMapper.mapToDtos(products);
    }

    public void addProduct(ProductRequestDto productDto) {
        Producer producer = producerRepository.findById(productDto.getProducerId())
                .orElseThrow(() -> new NoSuchElementException("Producer not found"));
        List<Long> ids = productDto.getProductAttributes() == null
                ? List.of()
                :productDto.getProductAttributes()
                    .stream()
                    .map(ProductAttributeDto::getAttributeId)
                    .distinct()
                    .toList();
        List<Attribute> attributes = attributeRepository.findAllById(ids);
        Map<Long, Attribute> attributeMap = attributes
                .stream()
                .collect(Collectors.toMap(
                        Attribute::getId,
                        attribute -> attribute
                ));
        Product product = productMapper.mapToCreateProduct(productDto, producer, attributeMap);
        productRepository.save(product);
    }

    public void updateProduct(Long id, ProductRequestDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        Producer producer = producerRepository.findById(productDto.getProducerId())
                .orElseThrow(() -> new NoSuchElementException("Producer not found"));
        List<Long> ids = productDto.getProductAttributes() == null
                ? List.of()
                :productDto.getProductAttributes()
                    .stream()
                    .map(ProductAttributeDto::getAttributeId)
                    .distinct()
                    .toList();
        List<Attribute> attributes = attributeRepository.findAllById(ids);
        Map<Long, Attribute> attributeMap = attributes
                .stream()
                .collect(Collectors.toMap(
                        Attribute::getId,
                        attribute -> attribute
                ));
        productMapper.mapToUpdateProduct(product, productDto, producer, attributeMap);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        productRepository.delete(product);
    }
}
