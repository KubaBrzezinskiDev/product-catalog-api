package com.kubadev.technicalassessmenttask.controller;

import com.kubadev.technicalassessmenttask.dto.ProductDto;
import com.kubadev.technicalassessmenttask.dto.ProductRequestDto;
import com.kubadev.technicalassessmenttask.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<ProductDto>> getAllFilteredProducts(@RequestParam List<Long> attributesIds){
        List<ProductDto> products = productService.getAllFilteredProducts(attributesIds);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> getAllSearchedProducts(@RequestParam String query){
        List<ProductDto> products = productService.getAllSearchedProducts(query);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@Valid @RequestBody ProductRequestDto productDto){
        productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequestDto productDto){
        productService.updateProduct(id, productDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
