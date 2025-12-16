package com.admin.backend.services;

import com.admin.backend.models.AboutUsModel;
import com.admin.backend.repositories.AboutUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutUsService {
    @Autowired
    private AboutUsRepository aboutUsRepository;
    
    public AboutUsModel fetchAboutUs(){
        if (aboutUsRepository.findAll().isEmpty()){
            return null;
        }
        return aboutUsRepository.findAll().getFirst();
    }
    
    public AboutUsModel createAboutUs(AboutUsModel aboutUs) {
        return aboutUsRepository.save(aboutUs);
    }
    
    public AboutUsModel updateAboutUs(AboutUsModel aboutUs){
        return aboutUsRepository.save(aboutUs);
    }

    public void deleteAboutUs(){
        aboutUsRepository.deleteAll();
    }
}
