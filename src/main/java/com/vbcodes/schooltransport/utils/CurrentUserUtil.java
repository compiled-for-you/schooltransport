package com.vbcodes.schooltransport.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vbcodes.schooltransport.configuration.CustomAppUser;
import com.vbcodes.schooltransport.entity.AppUser;

public class CurrentUserUtil {
    
    public static AppUser getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !(auth.getPrincipal() instanceof CustomAppUser)) {
            return null; // No authenticated user
        }
        CustomAppUser customAppUser = (CustomAppUser) auth.getPrincipal();
        return customAppUser.getAppUser();
    }
}
