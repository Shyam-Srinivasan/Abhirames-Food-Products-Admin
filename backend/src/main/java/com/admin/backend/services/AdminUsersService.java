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
    
    public AdminUsersModel createAdminUser(AdminUsersModel adminUsersModel){
        return adminUsersRepository.save(adminUsersModel);
    }
    
    public AdminUsersModel fetchAdminUserByAdminId(Long adminId){
        return adminUsersRepository.findByAdminId(adminId);
    }

    public AdminUsersModel fetchAdminUserByUsername(String username){
        return adminUsersRepository.findByUsername(username);
    }
    
    public List<AdminUsersModel> fetchAdminUsersByRole(String role) {
        return adminUsersRepository.findAllByRole(role);
    }
    
    public List<AdminUsersModel> fetchAdminUserByCreatedAtAfter(LocalDateTime createdAtAfter){
        return adminUsersRepository.findAllByCreatedAtAfter(createdAtAfter);
    }
    
    public List<AdminUsersModel> fetchAdminUserByCreatedAtBefore(LocalDateTime createdAtBefore){
        return adminUsersRepository.findAllByCreatedAtBefore(createdAtBefore);
    }
    
    public AdminUsersModel updateAdminUser(AdminUsersModel adminUsersModel){
        return adminUsersRepository.save(adminUsersModel);
    }
    
    public void deleteAdminUser(Long adminId){
        adminUsersRepository.deleteById(adminId);
    }
}
