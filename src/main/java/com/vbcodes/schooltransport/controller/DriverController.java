package com.vbcodes.schooltransport.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vbcodes.schooltransport.service.DriverService;
import com.vbcodes.schooltransport.entity.Driver;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService){
        this.driverService=driverService;
    }

    @GetMapping("/all")
    public List<Driver> getAllDrivers(Authentication auth, User user){
        System.out.println("Inside DriverController.getAllDrivers()");
        auth.getPrincipal();
        user.getAuthorities();
        return driverService.getAllDrivers();
    } 
}