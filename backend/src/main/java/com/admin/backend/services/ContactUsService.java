package com.admin.backend.services;

import com.admin.backend.models.AboutUsModel;
import com.admin.backend.models.ContactUsModel;
import com.admin.backend.repositories.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService {
    @Autowired
    private ContactUsRepository contactUsRepository;
    
    public ContactUsModel fetchContactUs(){
        return contactUsRepository.findAll().getFirst();
    }
    
    public ContactUsModel createContactUs(ContactUsModel contactUs){
        return contactUsRepository.save(contactUs);
    }
    
    public ContactUsModel updateContactUs(ContactUsModel contactUs) {
        return contactUsRepository.save(contactUs);
    }

    public void deleteAboutUs(){
        contactUsRepository.deleteAll();
    }
}
