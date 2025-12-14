package com.admin.backend.repositories;


import com.admin.backend.models.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<CategoriesModel, Long> {
    CategoriesModel findByCategoryId(Long categoryId);
}
