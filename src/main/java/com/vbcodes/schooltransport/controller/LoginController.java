package com.vbcodes.schooltransport.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    // @GetMapping("/login")
    // public String checkConnections(){
    //     return "home";
    // }

    @GetMapping("/")
    public String redirectToLogin(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String check(){
        return "login";
    }

    @GetMapping("/home")
    public String openHomePage(Authentication auth){
        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        System.out.println(userDetails);
        String userType="";
        if(userDetails.getAuthorities().size() == 1) {
            //has just one authority, this will be true everytime as of now, bcs every user has just one auth.
            for(GrantedAuthority r:userDetails.getAuthorities()){
                System.out.println(r.getAuthority());
                userType=r.getAuthority();
            }
        }
        return userType+"home";
    }

    @GetMapping("/getData")
    @ResponseBody
    public String getData(){
        // System.out.println("inside the controller");
        return "This is you api fetched data";
    }
}
