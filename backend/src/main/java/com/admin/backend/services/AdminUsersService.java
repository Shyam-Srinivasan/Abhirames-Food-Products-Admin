package com.admin.backend.services;

import com.admin.backend.models.AdminUsersModel;
import com.admin.backend.repositories.AdminUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminUsersService {
    @Autowired
    private AdminUsersRepository adminUsersRepository;
    
    public AdminUsersModel findAdminUserById(Long adminId){
        return adminUsersRepository.findByUserId(adminId);
    }

    public AdminUsersModel findAdminUserByUsername(String username){
        return adminUsersRepository.findByUsername(username);
    }

    public AdminUsersModel findAdminUserByEmail(String email){
        return adminUsersRepository.findByEmail(email);
    }
    
    public List<AdminUsersModel> findAdminUserByRole(String role) {
        return adminUsersRepository.findAllByRole(role);
    }
    
    public List<AdminUsersModel> findAdminUserByCreatedAtAfter(LocalDateTime createdAtAfter){
        return adminUsersRepository.findAllByCreatedAtAfter(createdAtAfter);
    }
    
    public List<AdminUsersModel> findAdminUserByCreatedAtBefore(LocalDateTime createdAtBefore){
        return adminUsersRepository.findAllByCreatedAtBefore(createdAtBefore);
    }
}
