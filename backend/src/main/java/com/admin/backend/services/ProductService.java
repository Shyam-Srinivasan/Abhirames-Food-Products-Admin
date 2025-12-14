package com.admin.backend.services;
import com.admin.backend.models.CategoriesModel;
import com.admin.backend.models.ProductsModel;
import com.admin.backend.repositories.CategoriesRepository;
import com.admin.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public List<ProductsModel> fetchAllProducts(){
        return productRepository.findAll();
    }
    
    public List<ProductsModel> fetchProductsByCategoryId(Long categoryId){
        return productRepository.findByCategoryId(categoryId);
    }
    
    public ProductsModel fetchProduct(Long productId){
        return productRepository.findByProductId(productId);
    }
    
    public ProductsModel fetchProductByName(String productName){
        return productRepository.findByProductName(productName);
    }

    public ProductsModel createProduct(ProductsModel product){
        return productRepository.save(product);
    }

    public ProductsModel updateProduct(ProductsModel product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

}
