package com.admin.backend.services;

import com.admin.backend.models.FAQsModel;
import com.admin.backend.repositories.FAQsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FAQsService {
    @Autowired
    private FAQsRepository faqsRepository;
    
    public FAQsModel createFAQs(FAQsModel faQsModel){
        return faqsRepository.save(faQsModel);
    }
    
    public List<FAQsModel> fetchAllFAQs(){
        return faqsRepository.findAll();
    }
    
    public FAQsModel fetchFAQsById(Long faqId){
        return faqsRepository.findByFaqId(faqId);
    }
    
    public boolean isQuestionPresent(String question){
        return faqsRepository.existsByQuestion(question);
    }
    
    public FAQsModel updateFAQs(FAQsModel faQsModel){
        return faqsRepository.save(faQsModel);
    }

    @Transactional
    public void deleteFAQs(Long faqId){
        faqsRepository.deleteById(faqId);
    }
}
