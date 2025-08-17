package com.vbcodes.schooltransport.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vbcodes.schooltransport.dto.VehicleDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.exception.customexceptions.IllegalResourceAccessException;
import com.vbcodes.schooltransport.exception.customexceptions.ResourceNotFoundException;
import com.vbcodes.schooltransport.repository.VehicleRepository;
import com.vbcodes.schooltransport.utils.CurrentUserUtil;

@Service
public class VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    private VehicleRepository vehicleRepository;
    private ModelMapper modelMapper;
    private OrgService orgService;
    private DriverService driverService;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, ModelMapper modelMapper, OrgService orgService, DriverService driverService) {
        this.driverService = driverService;
        this.orgService = orgService;
        this.vehicleRepository=vehicleRepository;
        this.modelMapper=modelMapper;
    }

    public List<VehicleDTO> getAllVehiclesForCurrentUser(){
        AppUser currentUser = CurrentUserUtil.getCurrentUser();
        String userType = currentUser.getRoles();

        List<Vehicle> allVehicles = new ArrayList<>();
        switch (userType) {
            case "ROLE_ORGANIZATION":
                logger.info("User Type: " + userType);
                // Organization currentOrganization = orgService.getOrganizationByAppUser(currentUser);
                allVehicles = vehicleRepository.findByOrganizationAppUser(currentUser);
            break;
            case "ROLE_DRIVER":
                logger.info("User Type: " + userType);
                Driver currentDriver = driverService.getDriverByAppUser(currentUser);
                //TODO check if the driver has a vehicle assigned in the mappings table (to be created) then return the vehicle
            break;
            case "ROLE_PARENT":
            logger.info("User Type: " + userType);
            //TODO if parent, return all vehicles assigned to the students of this parent
            break;
            default:
                return Collections.emptyList();
        }

        //map the vehicle entities to DTOs
        return allVehicles.stream()
                .map(this::mapFromEntityToDTO)
                .toList();
    }

    @Transactional
    public VehicleDTO addNewVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicleEntity=mapFromDTOToEntity(vehicleDTO);

        AppUser currentUser = CurrentUserUtil.getCurrentUser();
        Organization organization = orgService.getOrganizationByAppUser(currentUser);
        
        vehicleEntity.setOrganization(organization);
        Vehicle addedVehicle = vehicleRepository.save(vehicleEntity);
        return mapFromEntityToDTO(addedVehicle);
    }

    @Transactional
    public VehicleDTO updateVehicle(int vehicleID, VehicleDTO vehicleDTO){
        Vehicle vehicleEntity = vehicleRepository.findById(vehicleID)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + vehicleID));

        Organization organization = vehicleEntity.getOrganization();

        AppUser currentUser = CurrentUserUtil.getCurrentUser();
        Organization currentOrganization = orgService.getOrganizationByAppUser(currentUser);

        if(!organization.getOrgId().equals(currentOrganization.getOrgId())) {
            throw new IllegalResourceAccessException("Vehicle does not belong to the current organization");
        }

        vehicleEntity.setCapacity(vehicleDTO.getCapacity());
        vehicleEntity.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicleEntity.setVehicleRegistrationNumber(vehicleDTO.getVehicleRegistrationNumber());
        vehicleEntity.setVehicleType(vehicleDTO.getVehicleType());

        Vehicle updatedVehicle = vehicleRepository.save(vehicleEntity); 
        return mapFromEntityToDTO(updatedVehicle);
    }

    @Transactional
    public void deleteVehicle(int vehicleID) {
        Vehicle vehicleEntity = vehicleRepository.findById(vehicleID)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + vehicleID));
        
        AppUser currentUser = CurrentUserUtil.getCurrentUser();
        Organization currentOrganization = orgService.getOrganizationByAppUser(currentUser);

        if(vehicleEntity.getOrganization().getOrgId() != currentOrganization.getOrgId()) {
            throw new IllegalResourceAccessException("Vehicle does not belong to the current organization");
        }
        vehicleRepository.delete(vehicleEntity);
    }

    private Vehicle mapFromDTOToEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }

    private VehicleDTO mapFromEntityToDTO(Vehicle vehicleEntity){
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }

}
