package com.admin.backend.services;

import com.admin.backend.models.CategoriesModel;
import com.admin.backend.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    
    public List<CategoriesModel> fetchCategories(){
        return categoriesRepository.findAll();
    }
    
    public CategoriesModel fetchCategory(Long categoryId){
        return categoriesRepository.findByCategoryId(categoryId);
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
