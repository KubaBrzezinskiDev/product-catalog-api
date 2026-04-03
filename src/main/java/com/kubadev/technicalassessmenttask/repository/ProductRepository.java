package com.kubadev.technicalassessmenttask.repository;

import com.kubadev.technicalassessmenttask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("""
        SELECT DISTINCT p FROM Product p
        JOIN p.productAttributes pa
        WHERE pa.attribute.id IN :attributeIds
        GROUP BY p
        HAVING COUNT(DISTINCT pa.attribute.id) = :size
    """)
    public List<Product> findByAttributes(@Param("attributeIds") List<Long> attributeIds, @Param("size") long size);

    @Query("""
        SELECT DISTINCT p FROM Product p
        LEFT JOIN p.producer pr
        LEFT JOIN p.productAttributes pa
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(pr.name) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(pa.value) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    public List<Product> searchByParameter(@Param("query") String query);
}
