package com.vbcodes.schooltransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.AppUserDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.repository.AppUserRepository;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public Optional<AppUser> getAppUserById(int id){
        return appUserRepository.findById(id);
    }

    public AppUser getAppUserByUsername(String username){
        return appUserRepository.findByUsername(username);
    }

    public Optional<AppUser> getCurrentLoggedInUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username==null || username.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(appUserRepository.findByUsername(username));
    }

    public Integer getAppUserIdByUsername(String username){
        return appUserRepository.findUserIdByUsername(username);
    }

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public AppUser saveAppUser(AppUserDTO appUserDTO){
        AppUser appUser=new AppUser(appUserDTO.getUsername(), passwordEncoder.encode(appUserDTO.getPassword()), appUserDTO.getRoles());
        return appUserRepository.save(appUser);
    }

    public void deleteAppUserById(int id){
        appUserRepository.deleteById(id);
    }
}