package com.vbcodes.schooltransport.configuration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.service.AppUserService;

@Service
public class ProjectUserDetailsManager implements UserDetailsService{

    private AppUserService appUserService;

    @Autowired
    public ProjectUserDetailsManager(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=appUserService.getAppUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException("No details found for the user" + username);
        User user = new User(appUser.getUsername(), appUser.getPassword(), getListOfGrantedAuthorities(appUser.getRoles()));
        return user;
    }

    private ArrayList<GrantedAuthority> getListOfGrantedAuthorities(String roles){
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        // TODO - assuming only one authority/role here. Later add all of them one by one in this list.
        authorities.add(new SimpleGrantedAuthority(roles));
        return authorities;
    }
}