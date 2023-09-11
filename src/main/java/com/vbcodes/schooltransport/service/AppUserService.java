package com.vbcodes.schooltransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.repository.AppUserRepository;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Optional<AppUser> getAppUserById(int id){
        return appUserRepository.findById(id);
    }

    public AppUser getAppUserByUsername(String username){
        return appUserRepository.findByUsername(username);
    }

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public AppUser saveAppUser(AppUser appUser){
        return appUserRepository.save(appUser);
    }

    public void deleteAppUserById(int id){
        appUserRepository.deleteById(id);
    }
}