package com.vbcodes.schooltransport.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.VehicleDTO;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.repository.VehicleRepository;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, ModelMapper modelMapper){
        this.vehicleRepository=vehicleRepository;
        this.modelMapper=modelMapper;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public void addNewVehicle(VehicleDTO vehicleDTO, Organization organization){
        Vehicle vehicleEntity=mapFromDTOToEntity(vehicleDTO);
        vehicleEntity.setOrganization(organization);
        vehicleRepository.save(vehicleEntity);
    }

    private Vehicle mapFromDTOToEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }

    private VehicleDTO mapFromEntityToDTO(Vehicle vehicleEntity){
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }
}
