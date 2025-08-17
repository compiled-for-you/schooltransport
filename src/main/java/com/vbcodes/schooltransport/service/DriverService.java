package com.vbcodes.schooltransport.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbcodes.schooltransport.dto.DriverDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.exception.customexceptions.ResourceNotFoundException;
import com.vbcodes.schooltransport.repository.DriverRepository;
import com.vbcodes.schooltransport.utils.CurrentUserUtil;

@Service
public class DriverService {
    private AppUserService appUserService;
    private OrgService orgService;
    private StudentService studentService;
    private DriverRepository driverRepository;
    private ModelMapper modelMapper;    

    @Autowired
    public DriverService(AppUserService appUserService, OrgService orgService, StudentService studentService, DriverRepository driverRepository, ModelMapper modelMapper){
        this.appUserService=appUserService;
        this.orgService=orgService;
        this.studentService=studentService;
        this.driverRepository=driverRepository;
        this.modelMapper=modelMapper;
    }

    public List<DriverDTO> getAllDriversForCurrentUser(){
        AppUser currentUser = CurrentUserUtil.getCurrentUser();
        String userType = currentUser.getRoles();

        List<Driver> driversList = new ArrayList<>();

        switch (userType) {
            case "ROLE_ORGANIZATION":
                // Organization currentOrganization = orgService.getOrganizationByAppUser(currentUser);
                driversList = driverRepository.findByOrganizationAppUser(currentUser);
                break;
            case "ROLE_DRIVER":
                // Driver currentDriver = this.getDriverByAppUser(currentUser);
                // driversList.add(mapFromEntityToDTO(currentDriver));
                break;
            case "ROLE_PARENT": 
                //TODO if parent, return all drivers assigned to the students of this parent
                break;
            default:
                return Collections.emptyList();
        }

        return driversList.stream()
                .map(this::mapFromEntityToDTO)
                .toList();
    }

    @Transactional
    public void saveNewDriver(DriverDTO driverDTO) {
        Organization orgEntity=orgService.getOrganizationById(driverDTO.getOrgId()).orElseThrow(() -> new ResourceNotFoundException("No organization found with Org ID " + driverDTO.getOrgId()));
       
        AppUser driverAppUser = appUserService.saveAppUser(driverDTO);

        Driver driverEntity = mapFromDTOToEntity(driverDTO);

        driverEntity.setOrganization(orgEntity);
        driverEntity.setAppUser(driverAppUser);

        driverRepository.save(driverEntity);
    }

    public Driver getDriverByAppUser(AppUser appUser) {
        return driverRepository.findDriverByAppUser(appUser);
    }

    public DriverDTO mapFromEntityToDTO(Driver driverEntity){
        return modelMapper.map(driverEntity, DriverDTO.class);
    }

    public Driver mapFromDTOToEntity(DriverDTO driverDTO){
        return modelMapper.map(driverDTO, Driver.class);
    }
}
