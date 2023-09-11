package com.vbcodes.schooltransport.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vbcodes.schooltransport.service.DriverService;
import com.vbcodes.schooltransport.entity.Driver;

@RestController
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService){
        this.driverService=driverService;
    }

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers(){
        return driverService.getAllDrivers();
    } 
}