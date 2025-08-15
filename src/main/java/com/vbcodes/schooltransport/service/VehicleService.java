package com.vbcodes.schooltransport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.VehicleDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.repository.VehicleRepository;

@Service
public class VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    private VehicleRepository vehicleRepository;
    private ModelMapper modelMapper;
    private AppUserService appUserService;
    private OrgService orgService;
    private DriverService driverService;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, ModelMapper modelMapper, AppUserService appUserService, OrgService orgService, DriverService driverService) {
        this.driverService = driverService;
        this.orgService = orgService;
        this.appUserService = appUserService;
        this.vehicleRepository=vehicleRepository;
        this.modelMapper=modelMapper;
    }

    public List<Vehicle> getAllVehicles(){
        Optional<AppUser> appUser = appUserService.getCurrentLoggedInUser();
        String userType = appUser.get().getRoles();

        List<Vehicle> allVehicles = new ArrayList<>();
        //depends on the user type, 
        switch (userType) {
            //if organization, return all vehicles of the organization
            case "ROLE_ORGANIZATION":
                logger.error("User Type: " + userType);
                Organization currentOrganization = orgService.getOrganizationByAppUser(appUser.get());
                allVehicles = vehicleRepository.findByOrganizationOrgId(currentOrganization.getOrgId());
            break;
            //if driver, return the one vehicle assigned to the driver
            case "ROLE_DRIVER":
                logger.error("User Type: " + userType);
                Driver currentDriver = driverService.getDriverByAppUser(appUser.get());
                //TODO check if the driver has a vehicle assigned in the mappings table (to be created) then return the vehicle
            break;
            //TODO if parent, return all vehicles assigned to the students of this parent
            //check the students enrollment table in this case
            case "ROLE_PARENT":
                logger.error("User Type: " + userType);
            break;
            default:
                return null;
        }

        return allVehicles;
    }

    public void addNewVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicleEntity=mapFromDTOToEntity(vehicleDTO);
        //get the current logged in user
        Optional<AppUser> appUser = appUserService.getCurrentLoggedInUser();
        //get the organization of the current logged in user because the vehicle is added ONLY by the organization
        Organization organization = orgService.getOrganizationByAppUser(appUser.get());
        //set the organization to the vehicle entity
        vehicleEntity.setOrganization(organization);
        //finally save the vehicle entity
        vehicleRepository.save(vehicleEntity);
    }

    public void updateVehicle(int vehicleID, VehicleDTO vehicleDTO){
        //TODO check if the vehicleID exists
    }

    private Vehicle mapFromDTOToEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }

    private VehicleDTO mapFromEntityToDTO(Vehicle vehicleEntity){
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }
}
