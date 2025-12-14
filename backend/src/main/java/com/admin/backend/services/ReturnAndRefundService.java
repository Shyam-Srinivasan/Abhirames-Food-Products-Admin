package com.admin.backend.services;


import com.admin.backend.models.ReturnAndRefundModel;
import com.admin.backend.repositories.ReturnAndRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnAndRefundService {
    @Autowired
    private ReturnAndRefundRepository returnAndRefundRepository;
    
    public String fetchReturnAndRefundPolicy(){
        return returnAndRefundRepository.findAll().getFirst().getDescription();
    }
    
    public void createReturnAndRefundPolicy(ReturnAndRefundModel returnAndRefundModel){
        returnAndRefundRepository.save(returnAndRefundModel);
    }
    
    public void updateReturnAndRefundPolicy(ReturnAndRefundModel returnAndRefundModel){
        returnAndRefundRepository.save(returnAndRefundModel);
    }
    
    public void deleteReturnAndRefundPolicy(){
        returnAndRefundRepository.deleteAll();
    }
    
}
