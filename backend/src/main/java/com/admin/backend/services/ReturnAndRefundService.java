package com.admin.backend.services;


import com.admin.backend.models.ReturnAndRefundModel;
import com.admin.backend.repositories.ReturnAndRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnAndRefundService {
    @Autowired
    private ReturnAndRefundRepository returnAndRefundRepository;
    
    public ReturnAndRefundModel fetchReturnAndRefundPolicy(){
        if (returnAndRefundRepository.findAll().isEmpty()){
            return null;
        }
        return returnAndRefundRepository.findAll().getFirst();
    }
    
    public ReturnAndRefundModel createReturnAndRefundPolicy(ReturnAndRefundModel returnAndRefundModel){
        return returnAndRefundRepository.save(returnAndRefundModel);
    }
    
    public ReturnAndRefundModel updateReturnAndRefundPolicy(ReturnAndRefundModel returnAndRefundModel){
        return returnAndRefundRepository.save(returnAndRefundModel);
    }
    
    public void deleteReturnAndRefundPolicy(){
        returnAndRefundRepository.deleteAll();
    }
    
}
