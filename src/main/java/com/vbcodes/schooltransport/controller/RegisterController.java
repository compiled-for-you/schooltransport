package com.vbcodes.schooltransport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.dto.OrganizationDTO;
import com.vbcodes.schooltransport.service.OrgService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private OrgService orgService;

    @Autowired
    RegisterController(OrgService orgService){
        this.orgService=orgService;
    }

    @GetMapping("/organization")
    private void getregisterOrganization(){
        System.out.println("orgDTO");
    }

    @PostMapping("/organization")
    private void registerOrganization(@RequestBody OrganizationDTO orgDTO){
        System.out.println(orgDTO);
        orgService.saveNewOrganization(orgDTO);
    }
}