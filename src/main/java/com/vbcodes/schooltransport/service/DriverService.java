package com.vbcodes.schooltransport.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.DriverDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.repository.DriverRepository;

@Service
public class DriverService {
    private AppUserService appUserService;
    private DriverRepository driverRepository;
    private ModelMapper modelMapper;    

    public DriverService(AppUserService appUserService, DriverRepository driverRepository, ModelMapper modelMapper){
        this.appUserService=appUserService;
        this.driverRepository=driverRepository;
        this.modelMapper=modelMapper;
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public void saveNewDriver(DriverDTO driverDTO, Organization organizationEntity, AppUser driverAppUser) {
        Driver driverEntity = mapFromDTOToEntity(driverDTO);
        System.out.println(driverAppUser);
        driverEntity.setOrganization(organizationEntity);
        driverEntity.setAppUser(driverAppUser);
        driverRepository.save(driverEntity);
    }

    public Driver getCurrentLoggedInDriver(Authentication auth){
        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        Integer currentUserId = appUserService.getAppUserIdByUsername(userDetails.getUsername());
        return this.getDriverByUserId(currentUserId);
    }

    private Driver getDriverByUserId(Integer currentUserId) {
        return driverRepository.findDriverByUserId(currentUserId);
    }

    public DriverDTO mapFromEntityToDTO(Driver driverEntity){
        return modelMapper.map(driverEntity, DriverDTO.class);
    }

    public Driver mapFromDTOToEntity(DriverDTO driverDTO){
        return modelMapper.map(driverDTO, Driver.class);
    }
}
