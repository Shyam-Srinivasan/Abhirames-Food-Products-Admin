package com.admin.backend.repositories;

import com.admin.backend.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    ProductModel findByProductId(Long productId);
    ProductModel findByProductName(String productName);
    List<ProductModel> findAllByCategoryId(Long categoryId);
    
    List<ProductModel> findAllByIsAvailable(boolean isAvailable);
}
