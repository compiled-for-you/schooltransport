package com.vbcodes.schooltransport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckController {

    // @GetMapping("/login")
    // public String checkConnections(){
    //     return "home";
    // }

    @GetMapping("/login")
    public String check(){
        return "login";
    }

    @GetMapping("/getData")
    @ResponseBody
    public String getData(){
        // System.out.println("inside the controller");
        return "This is you api fetched data";
    }
}
