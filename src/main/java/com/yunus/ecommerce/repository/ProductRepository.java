package com.yunus.ecommerce.repository;

import com.yunus.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.category_id.categoryId = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") long categoryId);


}
