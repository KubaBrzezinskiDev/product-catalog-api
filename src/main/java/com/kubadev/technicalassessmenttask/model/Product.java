package com.kubadev.technicalassessmenttask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id",  nullable = false)
    private Producer producer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> productAttributes = new ArrayList<>();

    public void addProductAttribute(ProductAttribute productAttribute) {
        this.productAttributes.add(productAttribute);
        productAttribute.setProduct(this);
    }

    public void removeProductAttribute(ProductAttribute productAttribute) {
        this.productAttributes.remove(productAttribute);
        productAttribute.setProduct(null);
    }
}
