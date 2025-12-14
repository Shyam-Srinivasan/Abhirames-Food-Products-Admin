package com.admin.backend.services;

import com.admin.backend.models.FAQsModel;
import com.admin.backend.repositories.FAQsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FAQsService {
    @Autowired
    private FAQsRepository faqsRepository;
    
    public List<FAQsModel> fetchAllFAQs(){
        return faqsRepository.findAll();
    }
    
    public FAQsModel createFAQs(FAQsModel faQsModel){
        return faqsRepository.save(faQsModel);
    }
    
    public FAQsModel updateFAQs(FAQsModel faQsModel){
        return faqsRepository.save(faQsModel);
    }

    public void deleteFAQs(Long faqId){
        faqsRepository.deleteById(faqId);
    }
}
