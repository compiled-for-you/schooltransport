package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.dto.DriverDTO;
import com.vbcodes.schooltransport.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService){
        this.driverService=driverService;
    }

    @GetMapping
    public ResponseEntity<?> getAllDrivers(){
        List<DriverDTO> drivers = driverService.getAllDriversForCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(drivers);
    }

    
}