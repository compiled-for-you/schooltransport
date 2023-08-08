package com.vbcodes.schooltransport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckController {

    @GetMapping("/check1")
    public String checkConnections(){
        return "home";
    }

    @GetMapping("/check")
    @ResponseBody
    public String check(){
        return "homesf";
    }

    @GetMapping("/getData")
    @ResponseBody
    public String getData(){
        // System.out.println("inside the controller");
        return "This is you api fetched data";
    }
}
