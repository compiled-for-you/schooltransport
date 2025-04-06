package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.dto.VehicleDTO;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.service.AppUserService;
import com.vbcodes.schooltransport.service.OrgService;
import com.vbcodes.schooltransport.service.VehicleService;

@RestController
public class VehicleController {
    private VehicleService vehicleService;
    private OrgService orgService;
    private AppUserService appUserService;

    @Autowired
    public VehicleController(VehicleService vehicleService, OrgService orgService, AppUserService appUserService){
        this.vehicleService=vehicleService;
        this.orgService=orgService;
        this.appUserService=appUserService;
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/vehicles/add")
    public String addNewVehicle(){
        return "addvehicleform";
    }

    @PostMapping("/vehicles/add")
    public void addNewVehicle(@RequestBody VehicleDTO vehicleDTO, Authentication auth){
        Organization currentOrganization = orgService.getCurrentLoggedInOrganization(auth);
        System.out.println(currentOrganization);
        if(currentOrganization!=null)
            vehicleService.addNewVehicle(vehicleDTO, currentOrganization);
    }
}