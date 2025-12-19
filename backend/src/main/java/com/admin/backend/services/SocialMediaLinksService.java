package com.admin.backend.services;

import com.admin.backend.exceptions.ResourceConflictException;
import com.admin.backend.exceptions.ResourceNotFoundException;
import com.admin.backend.models.SocialMediaLinksModel;
import com.admin.backend.repositories.SocialMediaLinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SocialMediaLinksService {
    @Autowired
    private SocialMediaLinksRepository socialMediaLinksRepository;
    
    public SocialMediaLinksModel createSocialMediaLinks(SocialMediaLinksModel socialMediaLinksModel){
        if (socialMediaLinksRepository.existsByPlatformName(socialMediaLinksModel.getPlatformName())){
            throw new ResourceConflictException("Social media already exists with name: " + socialMediaLinksModel.getPlatformName());
        }
        return socialMediaLinksRepository.save(socialMediaLinksModel);
    }
    
    public SocialMediaLinksModel fetchSocialMedia(String platformName){
        if (platformName == null){
            throw new IllegalArgumentException("Platform name cannot be empty.");
        }
        
        SocialMediaLinksModel socialMedia = socialMediaLinksRepository.findByPlatformName(platformName);
        if (socialMedia == null){
            throw new ResourceNotFoundException("Provided social media not found!");
        }

        return socialMedia;
    }

    public List<SocialMediaLinksModel> fetchSocialMediaLinks(){
        if (socialMediaLinksRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("Social media links not found.");
        }
        return socialMediaLinksRepository.findAll();
    }
    
     public SocialMediaLinksModel updateSocialMediaLinks(SocialMediaLinksModel updatedSocialMediaLinksModel){
         if (updatedSocialMediaLinksModel == null || updatedSocialMediaLinksModel.getPlatformName() == null || updatedSocialMediaLinksModel.getProfileUrl() == null) {
             throw new IllegalArgumentException("One or more required fields are empty or null.");
         }
         
         if (!socialMediaLinksRepository.existsByPlatformName(updatedSocialMediaLinksModel.getPlatformName())){
             throw new ResourceConflictException("Unable to update social media details. No record exists for platform name: " + updatedSocialMediaLinksModel.getPlatformName());
         }
         
         SocialMediaLinksModel existingSocialMediaLinks = fetchSocialMedia(updatedSocialMediaLinksModel.getPlatformName());

         existingSocialMediaLinks.setPlatformName(updatedSocialMediaLinksModel.getPlatformName());
         existingSocialMediaLinks.setProfileUrl(updatedSocialMediaLinksModel.getProfileUrl());
         existingSocialMediaLinks.setActive(updatedSocialMediaLinksModel.isActive());
         existingSocialMediaLinks.setUpdatedAt(LocalDateTime.now());
         
        return socialMediaLinksRepository.save(existingSocialMediaLinks);
    }
    
    public String deleteSocialMediaLinks(String platformName){
        SocialMediaLinksModel existingSocialMedia = fetchSocialMedia(platformName);
        if (existingSocialMedia == null){
            throw new ResourceNotFoundException("Social media record not found for platform name: " + platformName);
        }
        socialMediaLinksRepository.delete(existingSocialMedia);
        return "Social media link deleted successfully.";
    }
}
