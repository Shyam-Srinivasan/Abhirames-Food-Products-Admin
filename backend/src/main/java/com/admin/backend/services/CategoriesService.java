package com.admin.backend.services;

import com.admin.backend.models.CategoriesModel;
import com.admin.backend.repositories.CategoriesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    
    public List<CategoriesModel> fetchCategories(){
        return categoriesRepository.findAll();
    }
    
    public CategoriesModel fetchCategoryById(Long categoryId){
        return categoriesRepository.findByCategoryId(categoryId);
    }
    
    public CategoriesModel fetchCategoryByName(String categoryName){
        return categoriesRepository.findByCategoryName(categoryName);
    }
    
    public List<CategoriesModel> fetchCategoriesByActiveStatus(boolean isActive){
        return categoriesRepository.findAllByIsActive(isActive);
    }
    
    public List<CategoriesModel> fetchCategoriesCreatedAfter(LocalDateTime createdAtAfter){
        return categoriesRepository.findAllByCreatedAtAfter(createdAtAfter);
    }
    
    public List<CategoriesModel> fetchCategoriesUpdatedAfter(LocalDateTime updatedAtBefore){
        return categoriesRepository.findAllByUpdatedAtAfter(updatedAtBefore);
    }
    
    public CategoriesModel createCategory(CategoriesModel category){
        return categoriesRepository.save(category);
    }

    public CategoriesModel updateCategory(CategoriesModel category){
        return categoriesRepository.save(category);
    }
    
    public void deleteCategory(Long categoryId){
        categoriesRepository.deleteById(categoryId);
    }
}
