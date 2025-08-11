package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.responses.ErrorResponse;
import com.vbcodes.schooltransport.service.OrgService;

@RestController
@RequestMapping("/organizations")
public class OrgController {
    // private static final Logger logger = LoggerFactory.getLogger(OrgController.class);
    private OrgService orgService;

    @Autowired
    public OrgController (OrgService orgService){
        this.orgService = orgService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllOrganizations(){
        List<Organization> allOrganizationsList = orgService.getAllOrganizations();
        if(allOrganizationsList.size()==0)
            return new ResponseEntity<>(new ErrorResponse("No Organizations Registered Yet!", null), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(allOrganizationsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable int id){ 
        Organization org = orgService.getOrganizationById(id).orElse(null);        
        return org;
    }

    @DeleteMapping("/{id}")
    public void deleteOrganizationById(@PathVariable int id){
        orgService.deleteOrganizationById(id);
    }
}