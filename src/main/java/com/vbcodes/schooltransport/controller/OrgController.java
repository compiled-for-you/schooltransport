package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.service.OrgService;

@RestController
public class OrgController {
    private static final Logger logger = LoggerFactory.getLogger(OrgController.class);
    private OrgService orgService;

    @Autowired
    public OrgController (OrgService orgService){
        this.orgService = orgService;
    }

    @GetMapping("/organizations")
    public List<Organization> getAllOrganizations(){ 
        return orgService.getAllOrganizations();
    }

    @GetMapping("/organizations/id")
    public Organization getOrganizationById(){ 
        logger.info("Inside get method before function call");
        Organization org = orgService.getOrganizationById(2).orElse(null);
        if(org==null) System.out.println("Org is coming null");
        else System.out.println(org);
        return org;
    }
}
