package com.vbcodes.schooltransport.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.OrganizationDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.repository.OrgRepository;

@Service
public class OrgService {
    private OrgRepository orgRepository;
    private AppUserService appUserService;
    private ModelMapper modelMapper;    
    private PasswordEncoder passwordEncoder;

    @Autowired
    public OrgService(OrgRepository orgRepository, ModelMapper modelMapper, AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.orgRepository = orgRepository;
        this.appUserService=appUserService;
        this.modelMapper = modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    public Optional<Organization> getOrganizationById(int id){
        return orgRepository.findById(id);
    }

    public List<Organization> getAllOrganizations() {
        return orgRepository.findAll();
    }

    public void saveNewOrganization(OrganizationDTO orgDTO){
        AppUser orgAppUser = appUserService.saveAppUser(new AppUser(orgDTO.getUsername(), passwordEncoder.encode(orgDTO.getPassword()), orgDTO.getRoles()));
        System.out.println(orgAppUser);
        Organization orgEntity = mapFromDTOToEntity(orgDTO);
        orgEntity.setAppUser(orgAppUser);
        System.out.println(orgEntity);
        orgRepository.save(orgEntity);
    }

    public void deleteOrganizationById(int id){
        orgRepository.deleteById(id);
    }

    public OrganizationDTO mapFromEntityToDTO(Organization orgEntity){
        return modelMapper.map(orgEntity, OrganizationDTO.class);
    }

    public Organization mapFromDTOToEntity(OrganizationDTO organizationDTO){
        return modelMapper.map(organizationDTO, Organization.class);
    }
}