package com.admin.backend.controllers;


import com.admin.backend.models.CategoriesModel;
import com.admin.backend.services.CategoriesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.1:3000", "http://192.168.1.2:3000", "http://192.168.1.3:3000", "http://192.168.1.4:3000", "http://192.168.1.5:3000", "http://192.168.1.6:3000", "http://192.168.1.7:3000", "http://192.168.1.8:3000", "http://192.168.1.9:3000"})

public class Controller {
    @Autowired
    private CategoriesService categoriesService;


//    @PostMapping("/categoryList/createCategory")
//    ResponseEntity<CategoriesModel> createCategory(@RequestBody CategoriesModel category) {
//        return new ResponseEntity<>(categoriesService.createCategory(category), HttpStatus.CREATED);
//    }

    @PostMapping("/categoryList/createCategory")
    ResponseEntity<CategoriesModel> createCategory(@RequestBody CategoriesModel category){
        return new ResponseEntity<>(categoriesService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/categoryList/updateCategory")
    ResponseEntity<CategoriesModel> updateCategory(@RequestParam Long categoryId, @RequestBody CategoriesModel updatedCategory) {
        try {
            if (updatedCategory == null || categoryId == null || updatedCategory.getCategoryName() == null) {
//                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            CategoriesModel category = categoriesService.fetchCategory(categoryId);
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            category.setCategoryId(categoryId);
            category.setCategoryName(updatedCategory.getCategoryName());

            CategoriesModel save = categoriesService.updateCategory(category);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoryList/fetchCategory")
    ResponseEntity<CategoriesModel> fetchCategory(@RequestParam Long categoryId) {
        try {
            CategoriesModel category = categoriesService.fetchCategory(categoryId);
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(category, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoryList/fetchCategories")
    ResponseEntity<List<CategoriesModel>> fetchCategories() {
        try {
            List<CategoriesModel> categories = categoriesService.fetchCategories();
            if (categories.isEmpty() || categories == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/categoryList/deleteCategory")
    ResponseEntity<CategoriesModel> deleteCategory(@RequestParam Long categoryId) {
        categoriesService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
