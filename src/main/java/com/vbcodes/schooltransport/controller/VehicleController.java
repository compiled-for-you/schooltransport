package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.service.VehicleService;

@RestController
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService=vehicleService;
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }
}
