package com.vbcodes.schooltransport.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.repository.DriverRepository;

@Service
public class DriverService {
    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository){
        this.driverRepository=driverRepository;
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }
}
