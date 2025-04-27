package com.vbcodes.schooltransport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/getData")
    public String getData(){
        return "This is you api fetched data";
    }
}
