package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.service.OrgService;

@RestController
public class OrgController {
    // private static final Logger logger = LoggerFactory.getLogger(OrgController.class);
    private OrgService orgService;

    @Autowired
    public OrgController (OrgService orgService){
        this.orgService = orgService;
    }

    @GetMapping("/organizations")
    public List<Organization> getAllOrganizations(){ 
        return orgService.getAllOrganizations();
    }

    @GetMapping("/organizations/{id}")
    public Organization getOrganizationById(@PathVariable int id){ 
        Organization org = orgService.getOrganizationById(id).orElse(null);        
        return org;
    }

    @DeleteMapping("/organization/{id}")
    public void deleteOrganizationById(@PathVariable int id){
        orgService.deleteOrganizationById(id);
    }
}