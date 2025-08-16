package com.vbcodes.schooltransport.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.service.AppUserService;

@Service
public class ProjectUserDetailsService implements UserDetailsService{

    private AppUserService appUserService;

    @Autowired
    public ProjectUserDetailsService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=appUserService.getAppUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException("No details found for the user" + username);
        return new CustomAppUser(appUser);
    }

}