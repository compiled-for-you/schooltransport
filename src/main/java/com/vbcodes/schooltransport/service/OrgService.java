package com.vbcodes.schooltransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.repository.OrgRepository;

@Service
public class OrgService {
    private OrgRepository orgRepository;

    @Autowired
    public OrgService (OrgRepository orgRepository){
        this.orgRepository=orgRepository;
    }

    public Optional<Organization> getOrganizationById(int id){
        return orgRepository.findById(id);
    }

    public List<Organization> getAllOrganizations() {
        return orgRepository.findAll();
    }
}
