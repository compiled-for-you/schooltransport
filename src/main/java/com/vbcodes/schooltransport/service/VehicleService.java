package com.vbcodes.schooltransport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.repository.VehicleRepository;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository=vehicleRepository;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }
}
