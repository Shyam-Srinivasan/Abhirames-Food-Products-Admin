package com.admin.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Products")
public class ProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @JsonProperty("product_id")
    private Long productId;

    @Column(name = "product_name")
    @JsonProperty("product_name")
    private String productName;
    
    @Column(name = "product_description")
    @JsonProperty("product_description")
    private String productDescription;
    
    @Column(name = "product_price")
    @JsonProperty("product_price")
    private BigDecimal productPrice;

    @Column(name = "product_image_url")
    @JsonProperty("product_image_url")
    private String productImageUrl;
    
    @Column(name = "is_available")
    @JsonProperty("is_available")
    private boolean isAvailable;
    
    @Column(name = "category_id")
    @JsonProperty("category_id")
    private Long categoryId;
    

    @Column(name = "created_at")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @JsonProperty("category")
    private CategoriesModel category;
}
