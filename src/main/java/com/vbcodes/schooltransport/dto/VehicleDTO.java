package com.vbcodes.schooltransport.dto;

import com.vbcodes.schooltransport.entity.VehicleType;

import lombok.Data;

@Data
public class VehicleDTO {

    private int vehicleId;
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String vehicleRegistrationNumber;
    private int capacity;

}
