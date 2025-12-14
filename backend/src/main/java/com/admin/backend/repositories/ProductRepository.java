package com.admin.backend.repositories;

import com.admin.backend.models.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductsModel, Long> {
    ProductsModel findByProductId(Long productId);
    ProductsModel findByProductName(String productName);
    List<ProductsModel> findByCategoryId(Long categoryId);
    
    
}
