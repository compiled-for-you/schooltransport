package com.vbcodes.schooltransport.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.OrganizationDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.repository.OrgRepository;

@Service
public class OrgService {
    private OrgRepository orgRepository;    
    private ModelMapper modelMapper;    

    @Autowired
    public OrgService(OrgRepository orgRepository, ModelMapper modelMapper) {
        this.orgRepository = orgRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Organization> getOrganizationById(int id){
        return orgRepository.findById(id);
    }

    public List<Organization> getAllOrganizations() {
        return orgRepository.findAll();
    }

    public Organization getOrganizationByUserId(int userId){
        return orgRepository.findOrganizationByUserId(userId);
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