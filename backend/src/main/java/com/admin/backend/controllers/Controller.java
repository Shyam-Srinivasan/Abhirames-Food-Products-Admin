package com.admin.backend.controllers;


import com.admin.backend.models.*;
import com.admin.backend.services.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.1:3000", "http://192.168.1.2:3000", "http://192.168.1.3:3000", "http://192.168.1.4:3000", "http://192.168.1.5:3000", "http://192.168.1.6:3000", "http://192.168.1.7:3000", "http://192.168.1.8:3000", "http://192.168.1.9:3000"})

public class Controller {
    @Autowired
    private CategoriesService categoriesService;
    
    @Autowired
    private AboutUsService aboutUsService;
    
    @Autowired
    private AdminUsersService adminUsersService;
    
    @Autowired
    private ContactUsService contactUsService;
    
    @Autowired
    private FAQsService faqsService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ReturnAndRefundService returnAndRefundService;
    
    @Autowired
    private SocialMediaLinksService socialMediaLinksService;
    
    
    // Category Service

    @PostMapping("/categories/createCategory")
    ResponseEntity<CategoriesModel> createCategory(@RequestBody CategoriesModel categoryModel){
        try{
            if (categoryModel == null || categoryModel.getCategoryName() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(categoriesService.fetchCategoryByName(categoryModel.getCategoryName()) != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            categoryModel.setCreatedAt(LocalDateTime.now());
            categoryModel.setUpdatedAt(LocalDateTime.now());
            
            return new ResponseEntity<>(categoriesService.createCategory(categoryModel), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/categories/fetchCategory")
    ResponseEntity<CategoriesModel> fetchCategory(@RequestParam Long categoryId) {
        try {
            CategoriesModel categoryModel = categoriesService.fetchCategoryById(categoryId);
            if (categoryModel == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(categoryModel, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories/fetchCategories")
    ResponseEntity<List<CategoriesModel>> fetchCategories() {
        try {
            List<CategoriesModel> categories = categoriesService.fetchCategories();
            if (categories == null || categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/categories/updateCategory")
    ResponseEntity<CategoriesModel> updateCategory(@RequestParam Long categoryId, @RequestBody CategoriesModel updatedCategory) {
        try {
            if (updatedCategory == null || updatedCategory.getCategoryName() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            CategoriesModel category = categoriesService.fetchCategoryById(categoryId);
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            category.setCategoryName(updatedCategory.getCategoryName());
            category.setUpdatedAt(LocalDateTime.now());

            CategoriesModel save = categoriesService.updateCategory(category);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/categories/deleteCategory")
    ResponseEntity<CategoriesModel> deleteCategory(@RequestParam Long categoryId) {
        try {
            CategoriesModel categoryModel = categoriesService.fetchCategoryById(categoryId);
            if (categoryModel == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            categoriesService.deleteCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // Product Service

    @PostMapping("/products/createProduct")
    ResponseEntity<ProductsModel> createProduct(@RequestBody ProductsModel productModel){
        try {
            if (productModel == null || productModel.getProductName() == null || productModel.getProductDescription() == null || productModel.getProductPrice() == null || productModel.getProductImageUrl() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (productService.fetchProductByName(productModel.getProductName()) != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            
            if (categoriesService.fetchCategoryById(productModel.getCategoryId()) == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            productModel.setCreatedAt(LocalDateTime.now());
            productModel.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(productService.createProduct(productModel), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("products/fetchProduct")
    ResponseEntity<ProductsModel> fetchProduct(@RequestParam Long productId){
        try {
            ProductsModel productModel = productService.fetchProduct(productId);
            if (productModel == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productModel, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("products/fetchProductsByCategory")
    ResponseEntity<List<ProductsModel>> fetchProductsByCategory(@RequestParam Long categoryId){
        try {
            List<ProductsModel> products = productService.fetchProductsByCategoryId(categoryId);
            if (products == null || products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("products/updateProduct")
    ResponseEntity<ProductsModel> updateProduct(@RequestParam Long productId, @RequestBody ProductsModel updatedProductModel){
        try {
            if (updatedProductModel == null || updatedProductModel.getProductName() == null || updatedProductModel.getProductDescription() == null || updatedProductModel.getProductPrice() == null || updatedProductModel.getProductImageUrl() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            ProductsModel product = productService.fetchProduct(productId);
            if (product == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            product.setProductName(updatedProductModel.getProductName());
            product.setProductDescription(updatedProductModel.getProductDescription());
            product.setProductPrice(updatedProductModel.getProductPrice());
            product.setProductImageUrl(updatedProductModel.getProductImageUrl());
            product.setAvailable(updatedProductModel.isAvailable());
            product.setCategoryId(updatedProductModel.getCategoryId());
            product.setUpdatedAt(LocalDateTime.now());
            
            ProductsModel save = productService.updateProduct(product);
            return new ResponseEntity<>(save, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("products/deleteProduct")
    ResponseEntity<ProductsModel> delete(@RequestParam Long productId){
        try {
            ProductsModel product = productService.fetchProduct(productId);
            if (product == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    // About Us Service

    @PostMapping("/aboutUs/createAboutUs")
    ResponseEntity<AboutUsModel> createAboutUs(@RequestBody AboutUsModel aboutUsModel){
        try{
            if (aboutUsModel == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (aboutUsService.fetchAboutUs() != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            aboutUsModel.setCreatedAt(LocalDateTime.now());
            aboutUsModel.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(aboutUsService.createAboutUs(aboutUsModel), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/aboutUs/fetchAboutUs")
    ResponseEntity<AboutUsModel> fetchAboutUs(){
        try {
            AboutUsModel aboutUsModel = aboutUsService.fetchAboutUs();
            if (aboutUsModel == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(aboutUsModel, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/aboutUs/updateAboutUs")
    ResponseEntity<AboutUsModel> updateAboutUs(@RequestBody AboutUsModel updatedAboutUsModel){
        try {
            if (updatedAboutUsModel == null || updatedAboutUsModel.getDescription() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            AboutUsModel aboutUs = aboutUsService.fetchAboutUs();
            
            aboutUsService.deleteAboutUs();
            aboutUs.setDescription(updatedAboutUsModel.getDescription());
            aboutUs.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(aboutUsService.updateAboutUs(aboutUs), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/aboutUs/deleteAboutUs")
    ResponseEntity<AboutUsModel> deleteAboutUs(){
        try {
            aboutUsService.deleteAboutUs();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // Social Media Links Controller

    @PostMapping("/socialMediaLinks/createSocialMediaLink")
    ResponseEntity<SocialMediaLinksModel> createSocialMediaLink(@RequestBody SocialMediaLinksModel socialMediaLinksModel){
        try {
            if (socialMediaLinksModel == null || socialMediaLinksModel.getPlatformName() == null || socialMediaLinksModel.getProfileUrl() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (socialMediaLinksService.fetchSocialMediaLinks() != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            
            socialMediaLinksModel.setCreatedAt(LocalDateTime.now());
            socialMediaLinksModel.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(socialMediaLinksService.createSocialMediaLinks(socialMediaLinksModel), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/socialMediaLinks/fetchSocialMediaLinks")
    ResponseEntity<SocialMediaLinksModel> fetchSocialMediaLinks(){
        try {
            SocialMediaLinksModel socialMediaLinksModel = socialMediaLinksService.fetchSocialMediaLinks();
            if (socialMediaLinksModel == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(socialMediaLinksModel, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/socialMediaLinks/updateSocialMediaLink")
    ResponseEntity<SocialMediaLinksModel> updateSocialMediaLink(@RequestBody SocialMediaLinksModel updatedSocialMediaLinksModel){
        try {
            if (updatedSocialMediaLinksModel == null || updatedSocialMediaLinksModel.getPlatformName() == null || updatedSocialMediaLinksModel.getProfileUrl() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            SocialMediaLinksModel socialMediaLinksModel = socialMediaLinksService.fetchSocialMediaLinks();
            
            if(socialMediaLinksModel == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            socialMediaLinksModel.setPlatformName(updatedSocialMediaLinksModel.getPlatformName());
            socialMediaLinksModel.setProfileUrl(updatedSocialMediaLinksModel.getProfileUrl());
            socialMediaLinksModel.setActive(updatedSocialMediaLinksModel.isActive());
            socialMediaLinksModel.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(socialMediaLinksService.updateSocialMediaLinks(socialMediaLinksModel), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/socialMediaLinks/deleteSocialMediaLinks")
    ResponseEntity<SocialMediaLinksModel> deleteSocialMediaLinks(){
        try {
            socialMediaLinksService.deleteSocialMediaLinks();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // Return and Refund Controller

    @PostMapping("/returnAndRefund/createReturnAndRefund")
    ResponseEntity<ReturnAndRefundModel> createReturnAndRefund(@RequestBody ReturnAndRefundModel returnAndRefundModel){
        try{
            if(returnAndRefundModel == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (returnAndRefundService.fetchReturnAndRefundPolicy() != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            
            returnAndRefundModel.setCreatedAt(LocalDateTime.now());
            returnAndRefundModel.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(returnAndRefundService.createReturnAndRefundPolicy(returnAndRefundModel), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/returnAndRefund/fetchReturnAndRefund")
    ResponseEntity<ReturnAndRefundModel> fetchReturnAndRefund(){
        try {
            ReturnAndRefundModel returnAndRefundModel = returnAndRefundService.fetchReturnAndRefundPolicy();
            if (returnAndRefundModel == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(returnAndRefundModel, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/returnAndRefund/updateReturnAndRefund")
    ResponseEntity<ReturnAndRefundModel> updateReturnAndRefund(@RequestBody ReturnAndRefundModel updatedReturnAndRefundModel){
        try{
            if(updatedReturnAndRefundModel == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            ReturnAndRefundModel returnAndRefundModel = returnAndRefundService.fetchReturnAndRefundPolicy();
            if (returnAndRefundModel == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            returnAndRefundModel.setDescription(updatedReturnAndRefundModel.getDescription());
            returnAndRefundModel.setUpdatedAt(LocalDateTime.now());
            
            System.out.println(returnAndRefundModel);
            
            return new ResponseEntity<>(returnAndRefundService.updateReturnAndRefundPolicy(returnAndRefundModel), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/returnAndRefund/deleteReturnAndRefund")
    ResponseEntity<ReturnAndRefundModel> deleteReturnAndRefund(){
        try {
            returnAndRefundService.deleteReturnAndRefundPolicy();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // FAQs Controller

    @PostMapping("/faqs/createFAQs")
    ResponseEntity<FAQsModel> createFAQs(@RequestBody FAQsModel faQsModel){
        try {
            if (faQsModel == null || faQsModel.getQuestion() == null || faQsModel.getAnswer() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            if (faqsService.isQuestionPresent(faQsModel.getQuestion())){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            faQsModel.setCreatedAt(LocalDateTime.now());
            faQsModel.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(faqsService.createFAQs(faQsModel), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/faqs/fetchAllFAQs")
    ResponseEntity<List<FAQsModel>> fetchAllFAQs(){
        try {
            List<FAQsModel> faqs = faqsService.fetchAllFAQs();
            if (faqs == null || faqs.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(faqs, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/faqs/updateFAQs")
    ResponseEntity<FAQsModel> updateFAQs(@RequestBody FAQsModel updatedFaqsModel){
        try {
            if (updatedFaqsModel == null || updatedFaqsModel.getQuestion() == null || updatedFaqsModel.getAnswer() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            FAQsModel faqsModel = faqsService.fetchFAQsById(updatedFaqsModel.getFaqId());
            if (faqsModel == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            faqsModel.setQuestion(updatedFaqsModel.getQuestion());
            faqsModel.setAnswer(updatedFaqsModel.getAnswer());
            faqsModel.setUpdatedAt(LocalDateTime.now());
            
            return new ResponseEntity<>(faqsService.updateFAQs(faqsModel), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/faqs/deleteFAQs")
    ResponseEntity<FAQsModel> deleteFAQs(@RequestParam Long faqId){
        try {
            FAQsModel faqsModel = faqsService.fetchFAQsById(faqId);
            if (faqsModel == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            faqsService.deleteFAQs(faqId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // Contact Us Controller

    @PostMapping("/contactUs/createContactUs")
    ResponseEntity<ContactUsModel> createContactUs(@RequestBody ContactUsModel contactUs){
        try {
            if (contactUs == null || contactUs.getPhoneNumber() == null || contactUs.getEmailId() == null || contactUs.getAddress() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (contactUsService.fetchContactUs() != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            
            contactUs.setCreatedAt(LocalDateTime.now());
            contactUs.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(contactUsService.createContactUs(contactUs), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/contactUs/fetchContactUs")
    ResponseEntity<ContactUsModel> fetchContactUs(){
        try{
            ContactUsModel contactUs = contactUsService.fetchContactUs();
            if (contactUs == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contactUsService.fetchContactUs(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/contactUs/updateContactUs")
    ResponseEntity<ContactUsModel> updateContactUs(@RequestBody ContactUsModel updatedContactUs){
        try {
            if (updatedContactUs == null || updatedContactUs.getPhoneNumber() == null || updatedContactUs.getEmailId() == null || updatedContactUs.getAddress() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            ContactUsModel contactUs = contactUsService.fetchContactUs();
            if (contactUs == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            contactUs.setPhoneNumber(updatedContactUs.getPhoneNumber());
            contactUs.setEmailId(updatedContactUs.getEmailId());
            contactUs.setAddress(updatedContactUs.getAddress());
            contactUs.setUpdatedAt(LocalDateTime.now());
            
            contactUsService.deleteAboutUs();

            return new ResponseEntity<>(contactUsService.updateContactUs(contactUs), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/contactUs/deleteContactUs")
    ResponseEntity<ContactUsModel> deleteContactUs(){
        try {
            contactUsService.deleteAboutUs();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // Admin Users Controller

    @PostMapping("/adminUsers/createAdminUser")
    ResponseEntity<AdminUsersModel> createAdminUser(@RequestBody AdminUsersModel adminUsersModel){
        try {
            if (adminUsersModel == null || adminUsersModel.getUsername() == null || adminUsersModel.getPassword() == null || adminUsersModel.getRole() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (adminUsersService.fetchAdminUserByUsername(adminUsersModel.getUsername()) != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            
            adminUsersModel.setCreatedAt(LocalDateTime.now());
            adminUsersModel.setUpdatedAt(LocalDateTime.now());
            return new ResponseEntity<>(adminUsersService.createAdminUser(adminUsersModel), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/adminUsers/fetchAdminUserById")
    ResponseEntity<AdminUsersModel> fetchAdminUserByAdminId(@RequestParam Long adminId){
        try {
            AdminUsersModel adminUsersModel = adminUsersService.fetchAdminUserByAdminId(adminId);
            if (adminUsersModel == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(adminUsersModel, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/adminUsers/fetchAdminUserByUserName")
    ResponseEntity<AdminUsersModel> fetchAdminUserByUserName(@RequestParam String username){
        try {
            AdminUsersModel adminUsersModel = adminUsersService.fetchAdminUserByUsername(username);
            if (adminUsersModel == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(adminUsersModel, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/adminUsers/fetchAdminUsersByRole")
    ResponseEntity<List<AdminUsersModel>> fetchAdminUsersByRole(@RequestParam String role){
        try {
            List<AdminUsersModel> adminUsers = adminUsersService.fetchAdminUsersByRole(role);
            if (adminUsers == null || adminUsers.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(adminUsers, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/adminUsers/fetchAdminUserByCreatedAtAfter")
    ResponseEntity<List<AdminUsersModel>> fetchAdminUserByCreatedAtAfter(@RequestParam LocalDateTime createdAtAfter){
        try {
            List<AdminUsersModel> adminUsers = adminUsersService.fetchAdminUserByCreatedAtAfter(createdAtAfter);
            if (adminUsers == null || adminUsers.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(adminUsers, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/adminUsers/fetchAdminUserByCreatedAtBefore")
    ResponseEntity<List<AdminUsersModel>> fetchAdminUserByCreatedAtBefore(@RequestParam LocalDateTime createdAtBefore){
        try {
            List<AdminUsersModel> adminUsers = adminUsersService.fetchAdminUserByCreatedAtBefore(createdAtBefore);
            if (adminUsers == null || adminUsers.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(adminUsers, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/adminUsers/updateAdminUser")
        ResponseEntity<AdminUsersModel> updateAdminUserModel(@RequestParam Long adminId, @RequestBody AdminUsersModel updatedAdminUserModel){
            try {
                if (updatedAdminUserModel == null || updatedAdminUserModel.getUsername() == null || updatedAdminUserModel.getPassword() == null || updatedAdminUserModel.getRole() == null || adminId == null){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                AdminUsersModel adminUsersModel = adminUsersService.fetchAdminUserByAdminId(adminId);
                if (adminUsersModel == null){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                
                adminUsersModel.setUsername(updatedAdminUserModel.getUsername());
                adminUsersModel.setPassword(updatedAdminUserModel.getPassword());
                adminUsersModel.setRole(updatedAdminUserModel.getRole());
                adminUsersModel.setUpdatedAt(LocalDateTime.now());

                return new ResponseEntity<>(adminUsersService.updateAdminUser(adminUsersModel), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
    
    @DeleteMapping("/adminUsers/deleteAdminUser")
    ResponseEntity<AdminUsersModel> deleteAdminUser(@RequestParam Long adminId){
        try{
            AdminUsersModel adminUsersModel = adminUsersService.fetchAdminUserByAdminId(adminId);
            if (adminUsersModel == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            adminUsersService.deleteAdminUser(adminId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
