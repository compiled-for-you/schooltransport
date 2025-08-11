package com.vbcodes.schooltransport.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.OrganizationDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.repository.OrgRepository;

@Service
public class OrgService {
    private AppUserService appUserService;
    private OrgRepository orgRepository;    
    private ModelMapper modelMapper;    

    @Autowired
    public OrgService(AppUserService appUserService, OrgRepository orgRepository, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.orgRepository = orgRepository;
        this.modelMapper = modelMapper;
    }

    public Organization getCurrentLoggedInOrganization(Authentication auth){
        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        Integer currentUserId = appUserService.getAppUserIdByUsername(userDetails.getUsername());
        // return this.getOrganizationByUserId(currentUserId);
        return null;
    }

    public Optional<Organization> getOrganizationById(int id){
        return orgRepository.findById(id);
    }

    public List<Organization> getAllOrganizations() {
        return orgRepository.findAll();
    }

    public Organization getOrganizationByAppUser(AppUser appUser){
        return orgRepository.findOrganizationByAppUser(appUser);
    }
    
    public void saveNewOrganization(OrganizationDTO orgDTO, AppUser orgAppUser){
        Organization orgEntity = mapFromDTOToEntity(orgDTO);
        orgEntity.setAppUser(orgAppUser);
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