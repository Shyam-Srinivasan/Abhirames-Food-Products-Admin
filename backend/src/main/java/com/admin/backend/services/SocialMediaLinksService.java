package com.admin.backend.services;

import com.admin.backend.models.SocialMediaLinksModel;
import com.admin.backend.repositories.SocialMediaLinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaLinksService {
    @Autowired
    private SocialMediaLinksRepository socialMediaLinksRepository;
    
    public SocialMediaLinksModel fetchSocialMediaLinks(){
        if (socialMediaLinksRepository.findAll().isEmpty()){
            return null;
        }
        return socialMediaLinksRepository.findAll().getFirst();
    }
    
    public SocialMediaLinksModel createSocialMediaLinks(SocialMediaLinksModel socialMediaLinksModel){
        return socialMediaLinksRepository.save(socialMediaLinksModel);
    }
    
    public SocialMediaLinksModel updateSocialMediaLinks(SocialMediaLinksModel socialMediaLinksModel){
        return socialMediaLinksRepository.save(socialMediaLinksModel);
    }
    
    public void deleteSocialMediaLinks(){
        socialMediaLinksRepository.deleteAll();
    }
}
