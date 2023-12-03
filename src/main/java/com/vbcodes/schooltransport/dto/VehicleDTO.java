package com.vbcodes.schooltransport.dto;

import com.vbcodes.schooltransport.entity.VehicleType;

public class VehicleDTO {
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String vehicleRegistrationNumber;
    private int capacity;
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public String toString() {
        return "VehicleDTO [vehicleType=" + vehicleType + ", vehicleNumber=" + vehicleNumber
                + ", vehicleRegistrationNumber=" + vehicleRegistrationNumber + ", capacity=" + capacity + "]";
    }

    
}
